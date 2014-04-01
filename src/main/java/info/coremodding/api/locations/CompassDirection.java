package info.coremodding.api.locations;

import info.coremodding.api.locations.exceptions.InvalidDirectionException;

import java.util.ArrayList;

/**
 * @author James Compass directions
 */
public enum CompassDirection
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
    
    private String                             StringValue;
    private final ArrayList<String>            alts        = new ArrayList<>();
    private static ArrayList<CompassDirection> edirections = new ArrayList<>();
    
    private CompassDirection(String name)
    {
        this.StringValue = name;
        addEDirection();
    }
    
    private CompassDirection(String name, String[] alts)
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
        CompassDirection.edirections.add(this);
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
    public static CompassDirection getFromName(String name)
    {
        for (CompassDirection edirection : edirections)
        {
            if (edirection.toString().equalsIgnoreCase(name)) return edirection;
            ArrayList<String> alts = edirection.getAlts();
            for (String alt : alts)
            {
                if (alt.equalsIgnoreCase(name)) return edirection;
            }
        }
        return CompassDirection.none;
    }
    
    /**
     * @param direction
     *            The e direction
     * @return The compass direction equivalent to that direction
     * @throws InvalidDirectionException
     *             The given direction is not valid
     *             Does not throw for all and none, those both return none.
     */
    public static CompassDirection getFromCompass(Direction direction)
            throws InvalidDirectionException
    {
        if (direction == Direction.north) { return CompassDirection.north; }
        if (direction == Direction.east) { return CompassDirection.east; }
        if (direction == Direction.south) { return CompassDirection.south; }
        if (direction == Direction.west) { return CompassDirection.west; }
        if (direction == Direction.none || direction == Direction.all) { return CompassDirection.none; }
        throw new InvalidDirectionException();
    }
}
