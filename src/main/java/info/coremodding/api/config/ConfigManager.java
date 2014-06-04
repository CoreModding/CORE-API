package info.coremodding.api.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Some forge config utilities
 */
public class ConfigManager extends Configuration
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
     * Creates a boolean property.
     * 
     * @param name
     *            Name of the property.
     * @param category
     *            Category of the property.
     * @param defaultValue
     *            Default value of the property.
     * @param comment
     *            A brief description what the property does.
     * @return The value of the new boolean property.
     */
    public boolean getBoolean(String name, String category,
            boolean defaultValue, String comment)
    {
        Property prop = this.get(category, name, defaultValue);
        prop.comment = comment + " [default: " + defaultValue + "]";
        return prop.getBoolean(defaultValue);
    }

    /**
     * Creates a float property.
     * 
     * @param name
     *            Name of the property.
     * @param category
     *            Category of the property.
     * @param defaultValue
     *            Default value of the property.
     * @param minValue
     *            Minimum value of the property.
     * @param maxValue
     *            Maximum value of the property.
     * @param comment
     *            A brief description what the property does.
     * @return The value of the new float property.
     */
    public float getFloat(String name, String category, float defaultValue,
            float minValue, float maxValue, String comment)
    {
        Property prop = this.get(category, name, Float.toString(defaultValue));
        prop.comment = comment + " [range: " + minValue + " ~ " + maxValue
                + ", default: " + defaultValue + "]";
        try
        {
            return Float.parseFloat(prop.getString()) < minValue ? minValue
                    : (Float.parseFloat(prop.getString()) > maxValue ? maxValue
                            : Float.parseFloat(prop.getString()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * Creates a integer property.
     * 
     * @param name
     *            Name of the property.
     * @param category
     *            Category of the property.
     * @param defaultValue
     *            Default value of the property.
     * @param minValue
     *            Minimum value of the property.
     * @param maxValue
     *            Maximum value of the property.
     * @param comment
     *            A brief description what the property does.
     * @return The value of the new integer property.
     */
    public int getInt(String name, String category, int defaultValue,
            int minValue, int maxValue, String comment)
    {
        Property prop = this.get(category, name, defaultValue);
        prop.comment = comment + " [range: " + minValue + " ~ " + maxValue
                + ", default: " + defaultValue + "]";
        return prop.getInt(defaultValue) < minValue ? minValue : (prop
                .getInt(defaultValue) > maxValue ? maxValue : prop
                .getInt(defaultValue));
    }
}
