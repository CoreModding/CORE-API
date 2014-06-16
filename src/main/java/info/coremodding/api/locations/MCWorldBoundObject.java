package info.coremodding.api.locations;

import net.minecraft.world.World;

/**
 * @author Zeus
 *         An object bound to an MC world
 */
public class MCWorldBoundObject extends Traveller
{

    /**
     * The world that this is bound to
     */
    public World world;

    /**
     * @param location
     *            The location this exists at
     * @param movement
     *            The way that this moves
     * @param world
     *            The world that this is bound to
     */
    public MCWorldBoundObject(Loc3 location, Velocity movement, World world)
    {
        super(location, movement);
        this.world = world;
    }

    /**
     * @return The world that this is bound to
     */
    public World getWorld()
    {
        return this.world;
    }

    /**
     * @param world
     *            The new world this is bound to
     */
    public void setWorld(World world)
    {
        this.world = world;
    }
}
