package info.coremodding.api.render;

import info.coremodding.api.exceptions.InvalidValueException;

/**
 * @author James
 *         A color
 */
public class Color
{
    
    private double red;
    private double green;
    private double blue;
    private double alpha;
    
    /**
     * @param r
     *            The red amount
     * @param g
     *            The green amount
     * @param b
     *            The blue amount
     * @param a
     *            The alpha amount
     * @throws InvalidValueException
     *             A number value was incorrect
     * 
     *             All values must be 1 or less and 0 or more.
     */
    public Color(double r, double g, double b, double a)
            throws InvalidValueException
    {
        setRed(r);
        setGreen(g);
        setBlue(b);
        setAlpha(a);
    }
    
    /**
     * @return The value of the red
     */
    public double getRed()
    {
        return this.red;
    }
    
    /**
     * @return The value of the green
     */
    public double getGreen()
    {
        return this.green;
    }
    
    /**
     * @return The value of the blue
     */
    public double getBlue()
    {
        return this.blue;
    }
    
    /**
     * @return The value of the alpha
     */
    public double getAlpha()
    {
        return this.alpha;
    }
    
    /**
     * @param r
     *            The red to set to
     * @throws InvalidValueException
     *             The number value was incorrect
     */
    public void setRed(double r) throws InvalidValueException
    {
        if (r > 1 || r < 0) throw new InvalidValueException(
                "Invalid color value. Must be between (or exactly) 0 or 1.");
        this.red = r;
    }
    
    /**
     * @param g
     *            The green to set to
     * @throws InvalidValueException
     *             The number value was incorrect
     */
    public void setGreen(double g) throws InvalidValueException
    {
        if (g > 1 || g < 0) throw new InvalidValueException(
                "Invalid color value. Must be between (or exactly) 0 or 1.");
        this.green = g;
    }
    
    /**
     * @param b
     *            The blue to set to
     * @throws InvalidValueException
     *             The number value was incorrect
     */
    public void setBlue(double b) throws InvalidValueException
    {
        if (b > 1 || b < 0) throw new InvalidValueException(
                "Invalid color value. Must be between (or exactly) 0 or 1.");
        this.blue = b;
    }
    
    /**
     * @param a
     *            The alpha to set to
     * @throws InvalidValueException
     *             The number value was incorrect
     */
    public void setAlpha(double a) throws InvalidValueException
    {
        if (a > 1 || a < 0) throw new InvalidValueException(
                "Invalid color value. Must be between (or exactly) 0 or 1.");
        this.alpha = a;
    }
}
