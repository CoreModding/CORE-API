package info.coremodding.api.plugin;


import info.coremodding.api.plugin.common.Info;
import info.coremodding.api.plugin.main.CoreAPIPlugin;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.LoaderException;
import cpw.mods.fml.common.toposort.TopologicalSort;


public class Loader
{
    private static Pattern zipJar = Pattern.compile("(.+).(zip|jar)$");
    private static Pattern modClass = Pattern.compile("(.+/|)(\\_[^\\s$]+).class$");

    /**
     * The state enum used to help track state progression for the loader
     * @author cpw
     *
     */
    private enum State
    {
        NOINIT, LOADING, PREINIT, INIT, POSTINIT, UP, ERRORED
    };

    /**
     * The singleton instance
     */
    private static Loader instance;
    /**
     * Our special logger for logging issues to. We copy various assets from the Minecraft logger to acheive a similar appearance.
     */
    public static Logger log = Logger.getLogger("CorePluginLoader");


    /**
     * Build information for tracking purposes.
     */
    private static String major;
    private static String minor;
    private static String rev;
    private static String build;
    private static String mcversion;

    /**
     * The {@link State} of the loader
     */
    private State state;
    /**
     * The class loader we load the mods into.
     */
    private PluginClassLoader modClassLoader;
    /**
     * The sorted list of mods.
     */
    public static List<ModPlugin> mods;
    /**
     * A named list of mods
     */
    private Map<String, ModPlugin> namedMods;
    /**
     * The canonical configuration directory
     */
    private File canonicalConfigDir;
    
    public static Loader instance()
    {
        if (instance == null)
        {
            instance = new Loader();
            
        }

        return instance;
    }

    private Loader()
    {
        Loader.log.setLevel(Level.ALL);
        try
        {
            File logPath =new File(Minecraft.getMinecraft().mcDataDir.getCanonicalPath(),"CorePluginLoader-%g.log");
            logPath.createNewFile();
        }
        catch (Exception e)
        {
            // Whatever - give up
        }
        
        log.info(String.format("Forge Mod Loader version %s.%s.%s.%s for Minecraft %s loading", major, minor, rev, build, mcversion));
    }

    /**
     * Sort the mods into a sorted list, using dependency information from the containers. The sorting is performed
     * using a {@link TopologicalSort} based on the pre- and post- dependency information provided by the mods.
     */
    @SuppressWarnings("static-access")
	private void sortModList()
    {
        log.fine("Verifying mod dependencies are satisfied");

        for (ModPlugin mod : mods)
        {
            if (!namedMods.keySet().containsAll(mod.getDependencies()))
            {
                log.severe(String.format("The mod %s requires mods %s to be available, one or more are not", mod.meta().name, mod.getDependencies()));
                LoaderException le = new LoaderException();
                log.throwing("Loader", "sortModList", le);
                throw new LoaderException();
            }
        }

        log.fine("All dependencies are satisfied");
        PluginSorter sorter = new PluginSorter(mods, namedMods);

        try
        {
            log.fine("Sorting mods into an ordered list");
            mods = sorter.sort();
            log.fine("Sorted mod list:");
            for (ModPlugin mod : mods)
            {
                log.fine(String.format("\t%s: %s (%s)", mod.meta().name, mod.getSource().getName(), mod.getSortingRules()));
            }
        }
        catch (IllegalArgumentException iae)
        {
            log.severe("A dependency cycle was detected in the input mod set so they cannot be loaded in order");
            log.throwing("Loader", "sortModList", iae);
            throw new LoaderException(iae);
        }
    }

    /**
     * The first mod initialization stage, performed immediately after the jar files and mod classes are loaded,
     * {@link State#PREINIT}. The mods are configured from their configuration data and instantiated (for BaseMod mods).
     */
    @SuppressWarnings("static-access")
	private void preModInit()
    {
        state = State.PREINIT;
        log.fine("Beginning mod pre-initialization");

        for (ModPlugin mod : mods)
        {
                log.finer(String.format("Pre-initializing %s", mod.getSource()));
                mod.preInit();
                namedMods.put(mod.meta().name, mod);
                mod.nextState();
        }

        log.fine("Mod pre-initialization complete");
    }

    /**
     * The main mod initialization stage, performed on the sorted mod list.
     */
    @SuppressWarnings("static-access")
	private void modInit()
    {
    	
        state = State.INIT;
        log.fine("Beginning mod initialization");

        for (ModPlugin mod : mods)
        {
            log.finer(String.format("Initializing %s", mod.meta().name));
            mod.init();
            mod.nextState();
        }

        log.fine("Mod initialization complete");
    }

    @SuppressWarnings("static-access")
	private void postModInit()
    {
        state = State.POSTINIT;
        log.fine("Beginning mod post-initialization");

        for (ModPlugin mod : mods)
        {
           
                log.finer(String.format("Post-initializing %s", mod.meta().name));
                mod.postInit();
                mod.nextState();
        }

        log.fine("Mod post-initialization complete");
    }

    /**
     * The primary loading code
     * 
     * This is visited during first initialization by Minecraft to scan and load the mods 
     * from all sources
     * 1. The minecraft jar itself (for loading of in jar mods- I would like to remove this if possible but forge depends on it at present)
     * 2. The mods directory with expanded subdirs, searching for mods named mod_*.class
     * 3. The mods directory for zip and jar files, searching for mod classes named mod_*.class again
     * 
     * The found resources are first loaded into the {@link #modClassLoader} (always) then scanned for class resources matching the specification above.
     * 
     * If they provide the {@link Mod} annotation, they will be loaded as "FML mods", which currently is effectively a NO-OP.
     * If they are determined to be {@link net.minecraft.src.BaseMod} subclasses they are loaded as such.
     * 
     * Finally, if they are successfully loaded as classes, they are then added to the available mod list.
     */
    private void load()
    {
        File minecraftDir = Minecraft.getMinecraft().mcDataDir;
        File modsDir = new File(minecraftDir, "mods");
        //File configDir = new File(minecraftDir, "config");
        String canonicalModsPath;

        try
        {
            minecraftDir.getCanonicalFile();
            canonicalModsPath = modsDir.getCanonicalPath();
        }
        catch (IOException ioe)
        {
            log.severe(String.format("Failed to resolve mods directory mods %s", modsDir.getAbsolutePath()));
            log.throwing("fml.server.Loader", "initialize", ioe);
            throw new LoaderException(ioe);
        }

        if (!modsDir.exists())
        {
            log.fine(String.format("No mod directory found, creating one: %s", canonicalModsPath));

            try
            {
                modsDir.mkdir();
            }
            catch (Exception e)
            {
                log.throwing("fml.server.Loader", "initialize", e);
                throw new LoaderException(e);
            }
        }

        if (!modsDir.isDirectory())
        {
            log.severe(String.format("Attempting to load mods from %s, which is not a directory", canonicalModsPath));
            LoaderException loaderException = new LoaderException();
            log.throwing("fml.server.Loader", "initialize", loaderException);
            throw loaderException;
        }


        state = State.LOADING;
        modClassLoader = new PluginClassLoader();
        log.fine("Attempting to load mods contained in the minecraft jar file and associated classes");
       
        File[] minecraftSources=modClassLoader.getParentSources();
        if (minecraftSources.length==1 && minecraftSources[0].isFile()) {
            log.fine(String.format("Minecraft is a file at %s, loading",minecraftSources[0].getAbsolutePath()));
            attemptFileLoad(minecraftSources[0]);
        } else {
            for (int i=0; i<minecraftSources.length; i++) {
                if (minecraftSources[i].isFile()) {
                    log.fine(String.format("Found a minecraft related file at %s, loading",minecraftSources[i].getAbsolutePath()));
                    attemptFileLoad(minecraftSources[i]);
                } else if (minecraftSources[i].isDirectory()) {
                    log.fine(String.format("Found a minecraft related directory at %s, loading",minecraftSources[i].getAbsolutePath()));
                    attemptDirLoad(minecraftSources[i],"");
                }
            }
        }
        log.fine("Minecraft jar mods loaded successfully");
        
        log.info(String.format("Loading mods from %s", canonicalModsPath));
        File[] modList = modsDir.listFiles();
        // Sort the files into alphabetical order first
        Arrays.sort(modList);

        for (File modFile : modList)
        {
            if (modFile.isDirectory())
            {
                log.fine(String.format("Found a directory %s, attempting to load it", modFile.getName()));
                boolean modFound = attemptDirLoad(modFile,"");

                if (modFound)
                {
                    log.fine(String.format("Directory %s loaded successfully", modFile.getName()));
                }
                else
                {
                    log.info(String.format("Directory %s contained no mods", modFile.getName()));
                }
            }
            else
            {
                Matcher matcher = zipJar.matcher(modFile.getName());

                if (matcher.matches())
                {
                    log.fine(String.format("Found a zip or jar file %s, attempting to load it", matcher.group(0)));
                    boolean modFound = attemptFileLoad(modFile);

                    if (modFound)
                    {
                        log.fine(String.format("File %s loaded successfully", matcher.group(0)));
                    }
                    else
                    {
                        log.info(String.format("File %s contained no mods", matcher.group(0)));
                    }
                }
            }
        }

        if (state == State.ERRORED)
        {
            log.severe("A problem has occured during mod loading, giving up now");
            throw new RuntimeException("Giving up please");
        }

        log.info(String.format("Forge Mod Loader has loaded %d mods", mods.size()));
    }

    private boolean attemptDirLoad(File modDir, String path)
    {
        if (path.length()==0) {
            extendClassLoader(modDir);
        }
        boolean foundAModClass = false;
        File[] content = modDir.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File file)
            {
                return (file.isFile() && modClass.matcher(file.getName()).find()) || file.isDirectory();
            }
        });

        // Always sort our content
        Arrays.sort(content);
        for (File file : content)
        {
            if (file.isDirectory()) {
                log.finest(String.format("Recursing into package %s", path+file.getName()));
                foundAModClass|=attemptDirLoad(file,path+file.getName()+".");
                continue;
            }
            Matcher fname = modClass.matcher(file.getName());
            if (!fname.find()) {
                continue;
            }
            String clazzName=path+fname.group(2);
            log.fine(String.format("Found a mod class %s in directory %s, attempting to load it", clazzName, modDir.getName()));
            loadModClass(modDir, file.getName(), clazzName);
            log.fine(String.format("Successfully loaded mod class %s", file.getName()));
            foundAModClass = true;
        }

        return foundAModClass;
    }

    private void loadModClass(File classSource, String classFileName, String clazzName)
    {
        try
        {
            Class<?> clazz = Class.forName(clazzName, false, modClassLoader);

            ModPlugin mod =null;
            if (clazz.isAnnotationPresent(Info.class))
           {
                // an CAP mod
                log.fine(String.format("Plugin class %s found, loading", clazzName));
                mod = CAPluginContainer.buildFor(clazz);
                mods.add(mod);
            }
            else{
            	
                // Unrecognized
            }
            if (mod!=null) {
                mods.add(mod);
                mod.nextState();
            }
        }
        catch (Exception e)
        {
            log.warning(String.format("Failed to load mod class %s in %s", classFileName, classSource.getAbsoluteFile()));
            log.throwing("fml.server.Loader", "attemptLoad", e);
            state = State.ERRORED;
        }
    }

    private void extendClassLoader(File file)
    {
        try
        {
            modClassLoader.addFile(file);
        }
        catch (MalformedURLException e)
        {
            throw new LoaderException(e);
        }
    }

    @SuppressWarnings("resource")
	private boolean attemptFileLoad(File modFile)
    {
        extendClassLoader(modFile);
        boolean foundAModClass = false;

        try
        {
            ZipFile jar = new ZipFile(modFile);

            for (ZipEntry ze : Collections.list(jar.entries()))
            {
                Matcher match = modClass.matcher(ze.getName());

                if (match.matches())
                {
                    String pkg = match.group(1).replace('/', '.');
                    String clazzName = pkg + match.group(2);
                    log.fine(String.format("Found a mod class %s in file %s, attempting to load it", clazzName, modFile.getName()));
                    loadModClass(modFile, ze.getName(), clazzName);
                    log.fine(String.format("Mod class %s loaded successfully", clazzName, modFile.getName()));
                    foundAModClass = true;
                }
            }
        }
        catch (Exception e)
        {
            log.warning(String.format("Zip file %s failed to read properly", modFile.getName()));
            log.throwing("fml.server.Loader", "attemptFileLoad", e);
            state = State.ERRORED;
        }

        return foundAModClass;
    }

    public static List<ModPlugin> getModList()
    {
        return Loader.mods;
    }

    /**
     * Called from the hook to start Plugin loading. We trigger the {@link #load()} and {@link #preModInit()} phases here.
     * Finally, the mod list is frozen completely and is consider immutable from then on.
     */
    public void loadPlugins()
    {
    	
        state = State.NOINIT;
        mods = new ArrayList<ModPlugin>();
        namedMods = new HashMap<String, ModPlugin>();
        load();
        preModInit();
        sortModList();
       
        // Make mod list immutable
       //mods = Collections.unmodifiableList(mods);
    }

    /**
     * Complete the initialization of the mods {@link #initializeMods()} and {@link #postModInit()} and mark ourselves up and ready to run.
     */
    public void initializePlugins()
    {
        modInit();
        postModInit();
      
        
        for (ModPlugin mod : getModList()) {
            mod.nextState();
        }
        state = State.UP;
        log.info(String.format("Core API Loader load complete, %d plugins loaded", mods.size()));
        
       
    }

    /**
     * Query if we know of a mod named modname
     * 
     * @param modname
     * @return
     */
    public static boolean isModLoaded(String modname)
    {
        return instance().namedMods.containsKey(modname);
    }

    /**
     * @return 
     */
    public File getConfigDir()
    {
        return canonicalConfigDir;
    }
    
}