package info.coremodding.api.locations;

import info.coremodding.api.locations.exceptions.InvalidDirectionException;

import java.util.ArrayList;

/**
 * @author James Compass directions
 */
public enum ECompassDirection
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
     * The direction is west
     */
    west("West");
    
    private String                              StringValue;
    private final ArrayList<String>             alts        = new ArrayList<>();
    private static ArrayList<ECompassDirection> edirections = new ArrayList<>();
    
    private ECompassDirection(String name)
    {
        this.StringValue = name;
        addEDirection();
    }
    
    private ECompassDirection(String name, String[] alts)
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
        ECompassDirection.edirections.add(this);
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
     *            east
     *            invalid
     *            none
     *            north
     *            null
     *            na
     *            n/a
     *            south
     *            west
     * @return The ECompassDirection with that name. Defaults to none.
     */
    public static ECompassDirection getFromName(String name)
    {
        for (ECompassDirection edirection : edirections)
        {
            if (edirection.toString().equalsIgnoreCase(name)) return edirection;
            ArrayList<String> alts = edirection.getAlts();
            for (String alt : alts)
            {
                if (alt.equalsIgnoreCase(name)) return edirection;
            }
        }
        return ECompassDirection.none;
    }
    
    /**
     * @param direction
     *            The e direction
     * @return The compass direction equivalent to that direction
     * @throws InvalidDirectionException
     *             The given direction is not valid
     *             Does not throw for all and none, those both return none.
     */
    public static ECompassDirection getFromCompass(EDirection direction)
            throws InvalidDirectionException
    {
        if (direction == EDirection.north) { return ECompassDirection.north; }
        if (direction == EDirection.east) { return ECompassDirection.east; }
        if (direction == EDirection.south) { return ECompassDirection.south; }
        if (direction == EDirection.west) { return ECompassDirection.west; }
        if (direction == EDirection.none || direction == EDirection.all) { return ECompassDirection.none; }
        throw new InvalidDirectionException();
    }
}
