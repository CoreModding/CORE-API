package info.coremodding.api.locations;

/**
 * @author Zeus
 * A location with an X and an Y
 */
public class Loc2
{

    /**
     * The X coordinate in this location
     */
    public double x = 0;
    
    /**
     * The Y coordinate in this location
     */
    public double y = 0;
    
    /**
     * @param x The X location
     * @param y The Y location
     */
    public Loc2(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * @return The X location
     */
    public double getX(){
        return this.x;
    }
    
    /**
     * @param x The new X location
     */
    public void setX(double x){
        this.x = x;
    }
    
    /**
     * @return The Y location
     */
    public double getY(){
        return this.y;
    }
    
    /**
     * @param y The new Y location
     */
    public void setY(double y){
        this.y = y;
    }
    
    /**
     * @param x The amount to add to the X coordinate
     * @return The new X coordinate
     */
    public double addX(double x){
        this.x += x;
        return x;
    }
    
    /**
     * @param x The amount to subtract from the X coordinate
     * @return The new X coordinate
     */
    public double subX(double x){
        this.x -= x;
        return x;
    }
    
    /**
     * @param y The amount to add to the X coordinate
     * @return The new X coordinate
     */
    public double addY(double y){
        this.y += y;
        return this.y;
    }
    
    /**
     * @param y The amount to subtract from the X coordinate
     * @return The new X coordinate
     */
    public double subY(double y){
        this.y -= y;
        return this.y;
    }
}
