package info.coremodding.api.locations;

/**
 * @author Zeus
 * A location with 3 coordinates
 */
public class Loc3 extends Loc2
{

    /**
     * The Z location in this location
     */
    public double z;
    
    /**
     * @param x The X location
     * @param y The Y location
     * @param z The Z location
     */
    public Loc3(double x, double y, double z)
    {
        super(x, y);
    }

    /**
     * @return The Z location
     */
    public double getZ(){
        return this.z;
    }
    
    /**
     * @param z The new Z location
     */
    public void setZ(double z){
        this.z = z;
    }
    
    /**
     * @param z The amount to add to the Z coordinate
     * @return The new Z coordinate
     */
    public double addZ(double z){
        this.z += z;
        return this.z;
    }
    
    /**
     * @param z The amount to subtract from the Z coordinate
     * @return The new Z coordinate
     */
    public double subZ(double z){
        this.z -= z;
        return this.z;
    }
}
