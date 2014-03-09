package info.coremodding.api.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Some forge config utilities
 */
public class ConfigCreator 
{
	
	/**
	 * The configuration instance
	 */
	public static Configuration config = new Configuration();
	
	/**
	 * Creates the config file itself
	 */
	public static void CreateConfig(FMLPreInitializationEvent event, String dir, String modidName)
	{
		new Configuration(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + dir + File.separator + modidName + ".cfg"));
	}
	
	/**
	 *  Gets a value
	 */
	public static void get(String category, String key, String defaultValue, String comment)
	{
		config.get(category, key, defaultValue, comment);
	}
	
	/**
	 *  Gets a value
	 */
	public static void get(String category, String key, boolean[] defaultValue, String comment)
	{
		config.get(category, key, defaultValue, comment);
	}
}
