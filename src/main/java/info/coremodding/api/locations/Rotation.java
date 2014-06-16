package info.coremodding.api.locations;

/**
 * @author Zeus
 *         A rotation with an X, Y, and Z rotation in degrees
 */
public class Rotation
{

    /**
     * The X rotation
     */
    public int xRotation = 0;

    /**
     * The Y rotation
     */
    public int yRotation = 0;

    /**
     * The Z rotation
     */
    public int zRotation;

    /**
     * @param x
     *            The X rotation
     * @param y
     *            The Y rotation
     * @param z
     *            The Z rotation
     */
    public Rotation(int x, int y, int z)
    {
        this.xRotation = x;
        this.yRotation = y;
        this.zRotation = z;
    }

    /**
     * @return The Z rotation
     */
    public int getZ()
    {
        return this.zRotation;
    }

    /**
     * @param z
     *            The new Z rotation
     */
    public void setZ(int z)
    {
        this.zRotation = z;
    }

    /**
     * @param z
     *            The amount to add to the Z rotation
     * @return The new Z rotation
     */
    public int addZ(int z)
    {
        this.zRotation += z;
        return this.zRotation;
    }

    /**
     * @param z
     *            The amount to subtract from the Z rotation
     * @return The new Z rotation
     */
    public int subZ(int z)
    {
        this.zRotation -= z;
        return this.zRotation;
    }

    /**
     * @return The X rotation
     */
    public int getX()
    {
        return this.xRotation;
    }

    /**
     * @param x
     *            The new X rotation
     */
    public void setX(int x)
    {
        this.xRotation = x;
    }

    /**
     * @param x
     *            The amount to add to the X rotation
     * @return The new X rotation
     */
    public int addX(int x)
    {
        this.xRotation += x;
        return this.xRotation;
    }

    /**
     * @param x
     *            The amount to subtract from the X rotation
     * @return The new X rotation
     */
    public int subX(int x)
    {
        this.xRotation -= x;
        return this.xRotation;
    }

    /**
     * @return The Y rotation
     */
    public int getY()
    {
        return this.yRotation;
    }

    /**
     * @param y
     *            The new Y rotation
     */
    public void setY(int y)
    {
        this.yRotation = y;
    }

    /**
     * @param y
     *            The amount to add to the Y rotation
     * @return The new Y rotation
     */
    public int addY(int y)
    {
        this.yRotation += y;
        return this.yRotation;
    }

    /**
     * @param y
     *            The amount to subtract from the Y rotation
     * @return The new Y rotation
     */
    public int subY(int y)
    {
        this.zRotation -= y;
        return this.zRotation;
    }
}
