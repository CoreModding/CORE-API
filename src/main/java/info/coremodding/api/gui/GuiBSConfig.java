package info.coremodding.api.gui;

import info.coremodding.api.gui.slot.GuiSlotMod;
import info.coremodding.api.plugin.Loader;
import info.coremodding.api.plugin.ModPlugin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.GuiModList;

public class GuiBSConfig extends GuiScreen
{
    String             guiTitle;
    public GuiScreen   parent;
    private int        listWidth;
    private GuiSlotMod modList;
    private int        selected = -1;
    private ModPlugin  selectedMod;
    private GuiButton  about, Done;
    
    public GuiBSConfig(GuiScreen parent)
    {
        this.parent = parent;
    }
    
    @Override
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        switch (par1GuiButton.id)
        {
            case 1:
                
                MinecraftHelper.displayGuiScreen(Minecraft.getMinecraft(), new GuiModList(new GuiMainMenu()));
                break;
            
            case 2:
                this.updateScreen();
                MinecraftHelper.displayGuiScreen(Minecraft.getMinecraft(), new GuiAbout(this));
                
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
    
    @SuppressWarnings("static-access")
    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
        this.modList.drawScreen(par1, par2, par3);
        this.drawCenteredString(this.fontRendererObj, "Plugin List", this.width / 2, 16, 0xFFFFFF);
        int offset = this.listWidth + 20;
        int shifty = 35;
        if (this.selectedMod != null)
        {
            GL11.glEnable(GL11.GL_BLEND);
            
            this.fontRendererObj.drawStringWithShadow(this.selectedMod.meta().name, offset, shifty, 0xFFFFFF);
            shifty += 12;
            
            shifty = drawLine(String.format("Version: %s (%s)", this.selectedMod.meta().name, this.selectedMod.meta().version), offset, shifty);
            shifty = drawLine(String.format("Mod ID: '%s' ", this.selectedMod.meta().name), offset, shifty);
            
            int rightSide = this.width - offset - 20;
            if (rightSide > 20)
            {
                this.getFontRenderer().drawSplitString(this.selectedMod.meta().description, offset, shifty + 10, rightSide, 0xDDDDDD);
            }
            shifty = drawLine(String.format("Description: %s", this.selectedMod.meta().description), offset, shifty);
            
        }
        GL11.glDisable(GL11.GL_BLEND);
        
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
    
    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @Override
    public void initGui()
    {
        this.buttonList.clear();
        
        this.about = new GuiButton(2, this.width / 2 - 100, this.height - 38, 98, 20, "About");
        this.Done = new GuiButton(1, this.width / 2 + 2, this.height - 38, 98, 20, I18n.format("gui.done", new Object[0]));
        
        this.buttonList.add(this.about);
        this.buttonList.add(this.Done);
        
        Loader.instance();
        for (ModPlugin mod : Loader.mods)
        {
            this.listWidth = Math.max(this.listWidth, getFontRenderer().getStringWidth(mod.getMeta().name) + 10);
            this.listWidth = Math.max(this.listWidth, getFontRenderer().getStringWidth(mod.getMeta().version) + 10);
        }
        
        this.listWidth = Math.min(this.listWidth, 150);
        Loader.instance();
        this.modList = new GuiSlotMod(this, Loader.mods, this.listWidth);
        
    }
    
    @Override
    protected void keyTyped(char c, int i)
    {
        
    }
    
    public boolean modIndexSelected(int var1)
    {
        return var1 == this.selected;
    }
    
    @Override
    protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);
        
    }
    
    public void selectModIndex(int var1)
    {
        this.selected = var1;
        Loader.instance();
        if (var1 >= 0 && var1 <= Loader.mods.size())
        {
            Loader.instance();
            this.selectedMod = Loader.mods.get(this.selected);
        }
        else
        {
            this.selectedMod = null;
        }
    }
    
    @Override
    public void updateScreen()
    {
        super.updateScreen();
    }
    
}