package info.coremodding.api.locations;

import info.coremodding.api.locations.exceptions.InvalidDirectionException;

import java.util.ArrayList;

/**
 * @author James The sides of things
 */
public enum EDirection
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
     * The direction is below
     */
    bottom("Bottom"),
    
    /**
     * The direction is east
     */
    east("East"),
    
    /**
     * The direction is north
     */
    north("North"),
    
    /**
     * The direction is south
     */
    south("South"),
    
    /**
     * The direction is above
     */
    top("Top"),
    
    /**
     * The direction is west
     */
    west("West");
    
    private String                       StringValue;
    private final ArrayList<String>      alts        = new ArrayList<>();
    private static ArrayList<EDirection> edirections = new ArrayList<>();
    
    private EDirection(String name)
    {
        this.StringValue = name;
        addEDirection();
    }
    
    private EDirection(String name, String[] alts)
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
        EDirection.edirections.add(this);
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
     *            The name of the EDirction. Valid:
     *            all
     *            bottom
     *            east
     *            invalid
     *            none
     *            north
     *            null
     *            na
     *            n/a
     *            south
     *            top
     *            west
     * @return The EDirection with that name.
     * @throws InvalidDirectionException
     *             The given name is invalid
     */
    public static EDirection getFromName(String name)
            throws InvalidDirectionException
    {
        for (EDirection edirection : edirections)
        {
            if (edirection.toString().equalsIgnoreCase(name)) return edirection;
            ArrayList<String> alts = edirection.getAlts();
            for (String alt : alts)
            {
                if (alt.equalsIgnoreCase(name)) return edirection;
            }
        }
        throw new InvalidDirectionException();
    }
    
    /**
     * @param direction
     *            The compass direction
     * @return The EDirection equivalent to that compassdirection
     */
    public static EDirection getFromCompass(ECompassDirection direction)
    {
        if (direction == ECompassDirection.north) { return EDirection.north; }
        if (direction == ECompassDirection.east) { return EDirection.east; }
        if (direction == ECompassDirection.south) { return EDirection.south; }
        if (direction == ECompassDirection.west) { return EDirection.west; }
        return EDirection.none;
    }
}
