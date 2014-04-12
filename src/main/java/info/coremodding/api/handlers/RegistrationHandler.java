package info.coremodding.api.handlers;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;

/**
 * @author MushroomLT
 * Handles blocks and items registration
 */
public class RegistrationHandler
{
    private static Map<String, Item> items = new HashMap<>();

    private static Map<String, Achievement> achievements = new HashMap<>();
    
    /** Registers armor set with the specified name
     * @param name Name of the armor (eg. "flameArmor")
     * @param helmet Helmet armor piece
     * @param chestplate Chestplate armor piece
     * @param leggings Leggings armor piece
     * @param boots Boots armor piece
     */
    public static void registerArmorSet(String name, Item helmet, Item chestplate, Item leggings, Item boots){
        items.put(name + "Helmet", helmet);
        items.put(name + "Chestplate", chestplate);
        items.put(name + "Leggings", leggings);
        items.put(name + "Boots", boots);
    }
    
    /** Registers the specified item with the specified name
     * @param name Name of the item to register
     * @param item Item to register
     */
    public static void registerItem(String name, Item item){
        items.put(name, item);
    }
    
    /** Registers the specified achievement with the specified name
     * @param name Name of the achievement to register
     * @param achievement Achievement to register
     */
    public static void registerAchievement(String name, Achievement achievement){
        achievements.put(name, achievement);
    }
    
    /** Gets the registered item based on the name
     * @param name Name for which to return the item
     * @return Item
     */
    public static Item getItem(String name){
        return items.get(name);
    }
    
    /** Gets the registered achievement based on the name
     * @param name Name for which to return the achievement
     * @return Achievement
     */
    public static Achievement getAchievement(String name){
        return achievements.get(name);
    }
    
    
    
    /**
     * Handles registration of items and blocks (Used internally by CoreAPI)
     */
    public static void handleRegistration(){
        for(String name : items.keySet()){
            GameRegistry.registerItem(items.get(name), name);
        }
        for(String name : achievements.keySet()){
            achievements.get(name).registerStat();
        }
    }
}
