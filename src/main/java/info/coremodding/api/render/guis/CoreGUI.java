package info.coremodding.api.render.guis;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

/**
 * @author James
 *         A GUI base
 */
public class CoreGUI extends GuiScreen
{
    
    /**
     * How wide the GUI is
     */
    public int              xSize;
    
    /**
     * How tall the GUI is
     */
    public int              ySize;
    
    /**
     * The picture to draw
     */
    public ResourceLocation backgroundimage;
    
    /**
     * @param width
     *            The width of the GUI
     * @param height
     *            The height of the GUI
     * @param background
     *            The background image of the GUI
     */
    public CoreGUI(int width, int height, ResourceLocation background)
    {
        this.xSize = width;
        this.ySize = height;
        this.backgroundimage = background;
    }
    
    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    
    @Override
    public void drawScreen(int par1, int _par2, float par3)
    {
        int par2 = _par2;
        this.mc.getTextureManager().bindTexture(this.backgroundimage);
        par2 = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize - 30) / 2;
        drawTexturedModalRect(par2, j, 0, 0, this.xSize, this.ySize);
    }
}
