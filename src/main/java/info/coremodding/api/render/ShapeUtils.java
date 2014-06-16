package info.coremodding.api.render;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4d;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glVertex2d;

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
    public static void drawRectangle(Color color, double x, double y,
            int height, int length)
    {
        glDisable(GL_LIGHTING);
        glDisable(GL_DEPTH_TEST);
        glPushMatrix();
        glColor4d(color.getRed(), color.getGreen(), color.getBlue(),
                color.getAlpha());
        glBegin(GL_QUADS);
        glVertex2d(x, y);
        glVertex2d(x + length, y);
        glVertex2d(x + length, y + height);
        glVertex2d(x, y + height);
        glEnd();
        glPopMatrix();
        glEnable(GL_LIGHTING);
        glEnable(GL_DEPTH_TEST);
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
