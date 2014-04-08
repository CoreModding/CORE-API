package info.coremodding.api.plugin;

import java.util.ArrayList;
import java.util.List;

public class Loader
{
    private static Loader          instance;
    
    public static Loader instance()
    {
        if (instance == null)
        {
            instance = new Loader();
            
        }
        
        return instance;
    }
    /**
     * The sorted list of Plugins.
     */
    public List<ModPlugin>  mods;
    
    
    public void loadPlugins()
    {
        mods = new ArrayList<ModPlugin>();
    }
    
    public static List<ModPlugin> getModList()
    {
        return instance().mods;
    }
    
    /**
     * Query if we know of a mod named modname
     * 
     * @param modname
     */
    public static boolean isModLoaded(String modname)
    {
        return instance().mods.contains(modname);
    }
}