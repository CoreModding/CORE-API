package info.coremodding.api.locations;

import java.util.ArrayList;

/**
 * @author James
 *         Relative sides to things
 */
public enum RelativeSide
{
    
    /**
     * All sides
     */
    all("All"),
    
    /**
     * No side
     */
    none("None", new String[] { "Null", "NA", "N/A", "Invalid" }),
    
    /**
     * The side is right
     */
    right("Right"),
    
    /**
     * The side is left
     */
    left("Left"),
    
    /**
     * The side is right
     */
    up("Up", new String[] { "Above" }),
    
    /**
     * The side is left
     */
    down("Down", new String[] { "Below" }),
    
    /**
     * The side is ahead
     */
    front("Front", new String[] { "Ahead" }),
    
    /**
     * The side is in back
     */
    back("Back", new String[] { "Behind" });
    
    private String                         StringValue;
    private final ArrayList<String>        alts        = new ArrayList<>();
    private static ArrayList<RelativeSide> edirections = new ArrayList<>();
    
    private RelativeSide(String name)
    {
        this.StringValue = name;
        addEDirection();
    }
    
    private RelativeSide(String name, String[] alts)
    {
        this.StringValue = name;
        for (String alt : alts)
        {
            this.alts.add(alt);
        }
        addEDirection();
    }
    
    private void addEDirection()
    {
        RelativeSide.edirections.add(this);
    }
    
    @Override
    public String toString()
    {
        return this.StringValue;
    }
    
    /**
     * @return The alternate names of this
     */
    public ArrayList<String> getAlts()
    {
        return this.alts;
    }
    
    /**
     * @param name
     *            The name of the CompassDirection. Valid:
     *            all
     *            right
     *            left
     *            invalid
     *            none
     *            below
     *            null
     *            na
     *            n/a
     *            behind
     *            ahead
     *            front
     *            back
     * @return The ECompassDirection with that name. Defaults to none.
     */
    public static RelativeSide getFromName(String name)
    {
        for (RelativeSide edirection : edirections)
        {
            if (edirection.toString().equalsIgnoreCase(name)) return edirection;
            ArrayList<String> alts = edirection.getAlts();
            for (String alt : alts)
            {
                if (alt.equalsIgnoreCase(name)) return edirection;
            }
        }
        return RelativeSide.none;
    }
}
