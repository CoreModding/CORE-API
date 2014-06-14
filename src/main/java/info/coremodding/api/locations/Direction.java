package info.coremodding.api.locations;

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
     * The direction is below
     */
    bottom("Bottom"),

    /**
     * The direction is east
     */
    east("East"),

    /**
     * No side
     */
    none("None"),

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

    private static ArrayList<Direction> edirections = new ArrayList<>();

    /**
     * @param name
     *            The name of the EDirction. Valid:
     *            all
     *            bottom
     *            east
     *            none
     *            north
     *            south
     *            top
     *            west
     * @return The EDirection with that name.
     * @throws Exception
     *             The given name is invalid
     */
    public static Direction getFromName(String name) throws Exception
    {
        for (Direction edirection : edirections)
        {
            if (edirection.toString().equalsIgnoreCase(name))
                return edirection;
        }
        return Direction.none;
    }
    
    private String StringValue;

    private Direction(String name)
    {
        this.StringValue = name;
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
}
