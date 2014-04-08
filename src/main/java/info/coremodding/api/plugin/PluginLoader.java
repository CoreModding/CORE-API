package info.coremodding.api.plugin;

/**
 * Handles detecting and loading Plugins.
 */
public class PluginLoader
{
    
    public static void addPlugin(ModPlugin e)
    {
        e.preInit();
        e.init();
        e.postInit();
        Loader.instance().mods.add(e);
    }
}