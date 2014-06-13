package info.coremodding.api.render;

/**
 * @author James
 *         A color
 */
public class Color
{

    private double alpha;
    private double blue;
    private double green;
    private double red;

    /**
     * @param r
     *            The red amount
     * @param g
     *            The green amount
     * @param b
     *            The blue amount
     * @param a
     *            The alpha amount
     * @throws Exception
     *             A number value was incorrect
     * 
     *             All values must be 1 or less and 0 or more.
     */
    public Color(double r, double g, double b, double a) throws Exception
    {
        setRed(r);
        setGreen(g);
        setBlue(b);
        setAlpha(a);
    }

    /**
     * @param r
     *            The red amount
     * @param g
     *            The green amount
     * @param b
     *            The blue amount
     * @param a
     *            The alpha amount
     * @throws Exception
     *             A number value was incorrect
     * 
     *             All values must be between 0 and 255.
     */
    public Color(int r, int g, int b, int a) throws Exception
    {
        setRed(r / 255f);
        setGreen(g / 255f);
        setBlue(b / 255f);
        setAlpha(a / 255f);
    }

    /**
     * @return The value of the alpha
     */
    public double getAlpha()
    {
        return this.alpha;
    }

    /**
     * @return The value of the blue
     */
    public double getBlue()
    {
        return this.blue;
    }

    /**
     * @return The value of the green
     */
    public double getGreen()
    {
        return this.green;
    }

    /**
     * @return The value of the red
     */
    public double getRed()
    {
        return this.red;
    }

    /**
     * @param a
     *            The alpha to set to
     * @throws Exception
     *             The number value was incorrect
     */
    public void setAlpha(double a) throws Exception
    {
        if (a > 1 || a < 0)
            throw new Exception(
                    "Invalid color value. Must be between (or exactly) 0 or 1.");
        this.alpha = a;
    }

    /**
     * @param b
     *            The blue to set to
     * @throws Exception
     *             The number value was incorrect
     */
    public void setBlue(double b) throws Exception
    {
        if (b > 1 || b < 0)
            throw new Exception(
                    "Invalid color value. Must be between (or exactly) 0 or 1.");
        this.blue = b;
    }

    /**
     * @param g
     *            The green to set to
     * @throws Exception
     *             The number value was incorrect
     */
    public void setGreen(double g) throws Exception
    {
        if (g > 1 || g < 0)
            throw new Exception(
                    "Invalid color value. Must be between (or exactly) 0 or 1.");
        this.green = g;
    }

    /**
     * @param r
     *            The red to set to
     * @throws Exception
     *             The number value was incorrect
     */
    public void setRed(double r) throws Exception
    {
        if (r > 1 || r < 0)
            throw new Exception(
                    "Invalid color value. Must be between (or exactly) 0 or 1.");
        this.red = r;
    }
}
