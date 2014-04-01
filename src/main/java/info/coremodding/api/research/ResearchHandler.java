package info.coremodding.api.research;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.item.Item;

/**
 * @author James
 *         Handles research
 */
public class ResearchHandler
{
    
    private static final ArrayList<Research>                      research = new ArrayList<>();
    private static final HashMap<Item, ArrayList<ItemWithInteger>> similar  = new HashMap<>();
    
    private static final class ItemWithInteger
    {
        public Item    item;
        public Integer integer;
        
        public ItemWithInteger(Item i, Integer i2)
        {
            this.item = i;
            this.integer = i2;
        }
    }
    
    /**
     * @param researchitem
     *            The research item to add
     */
    public static final void addResearch(Research researchitem)
    {
        ResearchHandler.research.add(researchitem);
    }
    
    /**
     * @return The research registered
     */
    public static final ArrayList<Research> getResearch()
    {
        return ResearchHandler.research;
    }
    
    /**
     * @return The list of similar items
     */
    public static final HashMap<Item, ArrayList<ItemWithInteger>> getSimilar()
    {
        return ResearchHandler.similar;
    }
    
    /**
     * @param item
     *            The item to add a similar item to
     * @param similaritem
     *            The similar item
     * @param similarValue
     *            The similarness to the similar item
     */
    public static final void addSimilar(Item item, Item similaritem,
            int similarValue)
    {
        ResearchHandler.similar.get(item).add(
                new ItemWithInteger(similaritem, similarValue));
    }
    
    /**
     * @param item
     *            The item needed
     * @param check
     *            The item to attempt to substitute
     * @param similarval
     *            How similar must the item be
     * @return Can you substitute the item for the other item
     */
    public static final boolean canResearchUsing(Item item, Item check,
            int similarval)
    {
        ItemWithInteger[] iwis = ResearchHandler.similar.get(item).toArray(
                new ItemWithInteger[] {});
        for (ItemWithInteger iwi : iwis)
        {
            if (iwi.item == check && iwi.integer <= similarval) { return true; }
        }
        return false;
    }
}
