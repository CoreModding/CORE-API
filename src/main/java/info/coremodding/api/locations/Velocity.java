package info.coremodding.api.locations;

/**
 * @author Zeus
 * A velocity
 */
public class Velocity
{

    /**
     * The direction of travel
     */
    public Rotation direction;
    
    /**
     * The rate of travel
     */
    public double speed;
    
    /**
     * @param direction The direction this velocity is in
     * @param speed The rate this is going at
     */
    public Velocity(Rotation direction, double speed){
        this.direction = direction;
        this.speed = speed;
    }
    
    /**
     * @return The direction of this velocity
     */
    public Rotation getDirection(){
        return this.direction;
    }
    
    /**
     * @return The speed this is going at
     */
    public double getSpeed(){
        return this.speed;
    }
    
    /**
     * @param direction The new direction
     */
    public void setDirection(Rotation direction){
        this.direction = direction;
    }
    
    /**
     * @param speed The new speed to go at
     */
    public void setSpeed(double speed){
        this.speed = speed;
    }
    
    /**
     * @param amount The amount to accelerate
     * @return The new speed
     */
    public double accelerate(double amount){
        this.speed += amount;
        return this.speed;
    }
    
    /**
     * @param amount The amount to decelerate
     * @return The new speed
     */
    public double decelerate(double amount){
        this.speed -= amount;
        return this.speed;
    }
}
