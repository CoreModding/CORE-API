package info.coremodding.api.render;

import org.lwjgl.opengl.GL11;

/**
 * @author James
 *         Utilities for drawing shapes
 */
public class ShapeUtils
{
    
    /**
     * Draw a rectangle
     * 
     * @param color
     *            The color to draw
     * @param x
     *            The x to start at
     * @param y
     *            The y to start at
     * @param height
     *            The height of the rectangle
     * @param length
     *            The length of the rectangle
     */
    public static void drawRectangle(Color color, double x, double y, int height, int length)
    {
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glPushMatrix();
        GL11.glColor4d(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x + length, y);
        GL11.glVertex2d(x + length, y + height);
        GL11.glVertex2d(x, y + height);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }
    
    /**
     * Draw a square
     * 
     * @param color
     *            The color to draw
     * @param x
     *            The x to start at
     * @param y
     *            The y to start at
     * @param length
     *            The length/height of the square
     */
    public static void drawSquare(Color color, double x, double y, int length)
    {
        drawRectangle(color, x, y, length, length);
    }
}
