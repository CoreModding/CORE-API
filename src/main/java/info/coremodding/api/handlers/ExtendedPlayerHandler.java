package info.coremodding.api.handlers;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * Handles extended player properties. <br>
 * 
 * Use getProperties to get handler for a specific player.
 * 
 * @author MushroomLT
 */
public class ExtendedPlayerHandler implements IExtendedEntityProperties
{
    
    /**
     * Extended property name
     */
    public final static String  EXT_PROP_NAME = "CoreModAttributes";
    
    private Map<String, String> nbtDataMap;
    
    /**
     * The constructor
     * 
     * @param player
     *            The player to handle properties for
     */
    public ExtendedPlayerHandler(EntityPlayer player)
    {
        this.nbtDataMap = new HashMap<>();
    }
    
    /**
     * Registers the ExtendedEntityProperties handler for the specified player
     * 
     * @param player
     *            Player to register
     */
    public static final void register(EntityPlayer player)
    {
        player.registerExtendedProperties(ExtendedPlayerHandler.EXT_PROP_NAME, new ExtendedPlayerHandler(player));
    }
    
    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagList properties = new NBTTagList();
        
        for (String key : this.nbtDataMap.keySet())
            properties.appendTag(new NBTTagString(key + "$SPLIT$" + this.nbtDataMap.get(key)));
        
        compound.setTag(EXT_PROP_NAME, properties);
    }
    
    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagList properties = (NBTTagList) compound.getTag(EXT_PROP_NAME);
        
        for (int i = 0; i < properties.tagCount(); i++)
        {
            String tag[] = properties.getStringTagAt(i).split("$SPLIT$");
            this.nbtDataMap.put(tag[0], tag[1]);
        }
    }
    
    @Override
    public void init(Entity entity, World world)
    {
        
    }
    
    /**
     * Puts a key/value to the player NBT data
     * 
     * @param key
     *            Key
     * @param value
     *            Value
     */
    public void put(String key, String value)
    {
        this.nbtDataMap.put(key, value);
    }
    
    /**
     * Gets a value from the NBT data map
     * 
     * @param key
     *            Key to look for
     * @return Value
     */
    public String get(String key)
    {
        return this.nbtDataMap.get(key);
    }
    
    /**
     * Gets ExtendedPlayerHandler for specified player
     * 
     * @param player
     *            Player to get properties for
     * @return Extended player properties
     */
    public static final ExtendedPlayerHandler getProperties(EntityPlayer player)
    {
        return (ExtendedPlayerHandler) player.getExtendedProperties(ExtendedPlayerHandler.EXT_PROP_NAME);
    }
}
