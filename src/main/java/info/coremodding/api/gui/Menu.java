package info.coremodding.api.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class Menu extends GuiScreen
{
    String                  guiTitle;
    private final GuiScreen parent;
    private GuiButton       plugins, Done;
    
    public Menu(GuiScreen parent)
    {
        this.parent = parent;
    }
    
    @Override
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        switch (par1GuiButton.id)
        {
            case 1:
                
                MinecraftHelper.displayGuiScreen(Minecraft.getMinecraft(), this.parent);
                break;
            
            case 2:
                this.updateScreen();
                MinecraftHelper.displayGuiScreen(Minecraft.getMinecraft(), new GuiBSConfig(this));
                
                break;
            default:
                break;
        }
    }
    
    public int drawLine(String line, int offset, int shifty)
    {
        this.fontRendererObj.drawString(line, offset, shifty, 0xd7edea);
        return shifty + 10;
    }
    
    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
        this.drawBackground(0);
        this.drawCenteredString(this.fontRendererObj, "Menu", this.width / 2, 16, 0xFFFFFF);
        super.drawScreen(par1, par2, par3);
    }
    
    public FontRenderer getFontRenderer()
    {
        
        return this.fontRendererObj;
    }
    
    public Minecraft getMinecraftInstance()
    {
        
        return this.mc;
    }
    
    // I Need this here because then the actionPerformed() method doesn't work.
    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @Override
    public void initGui()
    {
        this.buttonList.clear();
        
        this.plugins = new GuiButton(2, this.width / 2 - 100, this.height / 2, 98, 20, "Plugins");
        this.Done = new GuiButton(1, this.width / 2 + 2, this.height / 2, 98, 20, I18n.format("gui.done", new Object[0]));
        
        this.buttonList.add(this.plugins);
        this.buttonList.add(this.Done);
        
    }
    
    @Override
    protected void keyTyped(char c, int i)
    {
        
    }
    
    @Override
    protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);
        
    }
    
    @Override
    public void updateScreen()
    {
        super.updateScreen();
    }
    
}