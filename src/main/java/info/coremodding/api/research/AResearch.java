package info.coremodding.api.research;

import net.minecraft.item.Item;

/**
 * @author James
 *         All research combonations have this
 */
public abstract class AResearch
{
    
    /**
     * The constructor
     */
    public AResearch()
    {
        ResearchHandler.addResearch(this);
    }
    
    /**
     * @return The first item needed
     */
    public abstract Item i1();
    
    /**
     * @return The second item needed
     */
    public abstract Item i2();
    
    /**
     * @return The third item needed
     */
    @SuppressWarnings("static-method")
    public Item i3()
    {
        return null;
    }
    
    /**
     * @return The fourth item needed
     */
    @SuppressWarnings("static-method")
    public Item i4()
    {
        return null;
    }
    
    /**
     * @return The fifth item needed
     */
    @SuppressWarnings("static-method")
    public Item i5()
    {
        return null;
    }
    
    /**
     * @return The sixth item needed
     */
    @SuppressWarnings("static-method")
    public Item i6()
    {
        return null;
    }
    
    /**
     * @return The seventh item needed
     */
    @SuppressWarnings("static-method")
    public Item i7()
    {
        return null;
    }
    
    /**
     * @return The eight item needed
     */
    @SuppressWarnings("static-method")
    public Item i8()
    {
        return null;
    }
    
    /**
     * @return The ninth item needed
     */
    @SuppressWarnings("static-method")
    public Item i9()
    {
        return null;
    }
    
    /**
     * @return The tenth item needed
     */
    @SuppressWarnings("static-method")
    public Item i10()
    {
        return null;
    }
    
    /**
     * @return The chance out of 100 that an item will break in research
     */
    @SuppressWarnings("static-method")
    public int chanceItemBreak()
    {
        return 100;
    }
    
    /**
     * @return What level of similarity the research accepts.
     * 
     *         Example for stone:
     *         1. Would accept ores
     *         2. Would accept end stone
     *         3. Would accept nether rack
     *         4. Accepts anything with the same material
     */
    @SuppressWarnings("static-method")
    public int acceptSimilar()
    {
        return 2;
    }
}
