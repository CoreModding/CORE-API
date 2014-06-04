package info.coremodding.api.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author James, based on ChickenBones's
 *         As such, this class (and only this class) is under the LGPL
 * 
 *         Some font utilities
 */
@SideOnly(Side.CLIENT)
public class FontUtils
{
    
    /**
     * The minecraft font render instance
     */
    public static FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
    
    /**
     * Draws a centered string
     * 
     * @param s
     *            The string to draw
     * @param xCenter
     *            The screen center
     * @param y
     *            How high to draw it at
     * @param color
     *            The text color
     */
    public static void drawCenteredString(String s, int xCenter, int y, int color)
    {
        fontRenderer.drawString(s, xCenter - fontRenderer.getStringWidth(s) / 2, y, color);
    }
    
    /**
     * Draws a right-aligned string
     * 
     * @param s
     *            The string to draw
     * @param xRight
     *            How far to the right to draw it
     * @param y
     *            How high to draw it at
     * @param color
     *            The text color
     */
    public static void drawRightString(String s, int xRight, int y, int color)
    {
        fontRenderer.drawString(s, xRight - fontRenderer.getStringWidth(s), y, color);
    }
}
