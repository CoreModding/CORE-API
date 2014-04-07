package info.coremodding.api.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;


/**
 * @author cpw
 *
 */
public class GuiAbout extends GuiScreen
{
    private GuiScreen mainMenu;
    private int listWidth;
    /**
     * @param mainMenu
     */
    public GuiAbout(GuiScreen mainMenu)
    {
        this.mainMenu=mainMenu;
        listWidth =20;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void initGui()
    {
    	this.buttonList.add(new GuiButton(6, this.width / 2 - 100, this.height - 38, I18n.format("gui.done")));
        /*  configModButton = new GuiButton(20, 10, this.height - 60, this.listWidth, 20, "Config");
        disableModButton = new GuiButton(21, 10, this.height - 38, this.listWidth, 20, "Disable");
        this.buttonList.add(configModButton);
        this.buttonList.add(disableModButton);
   */
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.enabled)
        {
            switch (button.id)
            {
                case 6:
                	this.mc.displayGuiScreen(this.mainMenu);
                    return;
            }
        }
        super.actionPerformed(button);
    }

    public int drawLine(String line, int offset, int shifty)
    {
        this.fontRendererObj.drawString(line, offset, shifty, 0xd7edea);
        return shifty + 12;
    }

    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int p_571_1_, int p_571_2_, float p_571_3_)
    {
    	super.drawBackground(0);
    	GL11.glEnable(GL11.GL_BLEND);
    	this.drawCenteredString(this.fontRendererObj, "About", this.width / 2, 16, 0xFFFFFF);
    	 int offset = this.listWidth  + 20;
         int shifty = 35;
         shifty = drawLine("this is a new system for loading addons for Core-API and othe mods", offset, shifty);
         shifty = drawLine("this was Created by RoboRave", offset, shifty);
         GL11.glDisable(GL11.GL_BLEND);
       super.drawScreen(p_571_1_, p_571_2_, p_571_3_);
    }

    Minecraft getMinecraftInstance() {
        /**
         * Reference to the Minecraft object.
         */
        return mc;
    }

    FontRenderer getFontRenderer() {
        /**
         * The FontRenderer used by GuiScreen
         */
        return fontRendererObj;
    }

}