package info.coremodding.api.locations;

/**
 * @author Zeus
 * An object
 */
public class Traveller
{

    /**
     * The location this is at
     */
    public Loc3 location;
    
    /**
     * The way this is moving
     */
    public Velocity movement;
    
    /**
     * @param location The location this is at
     * @param movement The way this is moving
     */
    public Traveller(Loc3 location, Velocity movement){
        this.location = location;
        this.movement = movement;
    }
    
    /**
     * @return The location this exists at
     */
    public Loc3 getLocation(){
        return this.location;
    }
    
    /**
     * @return The velocity this is moving at
     */
    public Velocity getVelocity(){
        return this.movement;
    }
    
    /**
     * @param location The new location
     */
    public void setLocation(Loc3 location){
        this.location = location;
    }
    
    /**
     * @param movement The new movement
     */
    public void setMovement(Velocity movement){
        this.movement = movement;
    }
}
