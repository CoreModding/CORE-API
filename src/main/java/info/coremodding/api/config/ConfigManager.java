package info.coremodding.api.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Some forge config utilities
 */
public class ConfigManager
{
    
    /**
     * The configuration instance
     */
    public static Configuration config = new Configuration();
    
    /**
     * Creates the config file itself
     * 
     * @param event
     *            The event used to get the directory
     * @param dir
     *            The sub-directory
     * @param modidName
     *            The mod id to name the config
     */
    public static void CreateConfig(FMLPreInitializationEvent event,
            String dir, String modidName)
    {
        config = new Configuration(new File(event
                .getModConfigurationDirectory().getAbsolutePath()
                + File.separator + dir + File.separator + modidName + ".cfg"));
    }
    
    /**
     * Gets a value
     */
    @SuppressWarnings("javadoc")
    public static void get(String category, String key, String defaultValue,
            String comment)
    {
        config.get(category, key, defaultValue, comment);
    }
    
    /**
     * Gets a value
     */
    @SuppressWarnings("javadoc")
    public static void get(String category, String key, boolean[] defaultValue,
            String comment)
    {
        config.get(category, key, defaultValue, comment);
    }
}
