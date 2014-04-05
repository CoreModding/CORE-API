package info.coremodding.api.locations;

import info.coremodding.api.locations.exceptions.InvalidDirectionException;

import java.util.ArrayList;

/**
 * @author James The sides of things
 */
public enum Direction
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
    
    private String                      StringValue;
    private final ArrayList<String>     alts        = new ArrayList<>();
    private static ArrayList<Direction> edirections = new ArrayList<>();
    
    private Direction(String name)
    {
        this.StringValue = name;
        addEDirection();
    }
    
    private Direction(String name, String[] alts)
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
        Direction.edirections.add(this);
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
    public static Direction getFromName(String name)
            throws InvalidDirectionException
    {
        for (Direction edirection : edirections)
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
    public static Direction getFromCompass(CompassDirection direction)
    {
        if (direction == CompassDirection.north) { return Direction.north; }
        if (direction == CompassDirection.east) { return Direction.east; }
        if (direction == CompassDirection.south) { return Direction.south; }
        if (direction == CompassDirection.west) { return Direction.west; }
        return Direction.none;
    }
}
