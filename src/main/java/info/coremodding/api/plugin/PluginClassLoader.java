package info.coremodding.api.plugin;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;

import cpw.mods.fml.common.LoaderException;

/**
 * A simple delegating class loader used to load mods into the system
 * 
 * 
 * @author cpw
 *
 */
public class PluginClassLoader extends URLClassLoader
{

    public PluginClassLoader()
    {
        super(new URL[0], PluginClassLoader.class.getClassLoader());
    }

    public void addFile(File modFile) throws MalformedURLException
    {
        ClassLoader cl=getParent();
        if (cl instanceof URLClassLoader) {
            URLClassLoader ucl=(URLClassLoader) cl;
            URL url = modFile.toURI().toURL();
            try {
                Method addUrl=URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                addUrl.setAccessible(true);
                addUrl.invoke(ucl, url);
            } catch (Exception e) {
                Loader.log.severe("A fatal error occured attempting to load a file into the classloader");
                throw new LoaderException(e);
            }
        }
    }
    
    public File[] getParentSources() {
        ClassLoader cl=getParent();
        if (cl instanceof URLClassLoader) {
            URLClassLoader ucl=(URLClassLoader) cl;
            URL[] pUrl=ucl.getURLs();
            File[] sources=new File[pUrl.length];
            try
            {
                for (int i=0; i<pUrl.length; i++) {
                    sources[i]=new File(pUrl[i].toURI());
                }
                return sources;
            }
            catch (URISyntaxException e)
            {
                Loader.log.throwing("PluginClassLoader", "getParentSources", e);
            }
        }
        Loader.log.severe("Unable to process our input to locate the minecraft code");
        throw new LoaderException();
    }
}