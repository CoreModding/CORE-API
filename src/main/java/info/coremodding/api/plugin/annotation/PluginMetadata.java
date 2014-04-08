package info.coremodding.api.plugin.annotation;

import info.coremodding.api.plugin.ModPlugin;

public class PluginMetadata
{
    public String                             modId;
    public static String                      name;
    public String                             description = "";
    public static String                      version;
    
    public Info                               modDescriptor;
    
    private static Class<? extends ModPlugin> clazz;
    
    public static void init()
    {
        
        name = clazz.getAnnotation(Info.class).name();
        version = clazz.getAnnotation(Info.class).version();
    }
    
    public PluginMetadata(Class<? extends ModPlugin> clazz)
    {
        PluginMetadata.clazz = clazz;
        modDescriptor = clazz.getAnnotation(Info.class);
    }
}
