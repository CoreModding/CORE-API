package mods.roborave.edm.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ConfigCreator 
{
	
	public static Configuration config= new Configuration();
	
	public static void CreateConfig(FMLPreInitializationEvent event, String dir, String modidName)
	{
		new Configuration(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + dir + File.separator + modidName + ".cfg"));
	}
	public static void get(String category, String key, String defaultValue, String comment)
	{
		config.get(category, key, defaultValue, comment);
	}
	public static void get(String category, String key, boolean[] defaultValue, String comment)
	{
		config.get(category, key, defaultValue, comment);
	}
}
