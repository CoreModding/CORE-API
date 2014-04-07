
package info.coremodding.api.gui.slot;

import info.coremodding.api.gui.GuiBSConfig;
import info.coremodding.api.plugin.Loader;
import info.coremodding.api.plugin.ModPlugin;

import java.util.List;

import net.minecraft.client.renderer.Tessellator;
import cpw.mods.fml.client.GuiScrollingList;

/**
 * @author RoboRave
 *
 */
public class GuiSlotMod extends GuiScrollingList
{
    private GuiBSConfig parent;
    private List<ModPlugin> mods;

    public GuiSlotMod(GuiBSConfig parent, List<ModPlugin> mods2, int listWidth)
    {
        super(parent.getMinecraftInstance(), listWidth, parent.height, 32, parent.height - 66 + 4, 10, 35);
        this.parent=parent;
        this.mods=mods2;
    }

    @Override
    protected int getSize()
    {
        return mods.size();
    }

    @Override
    protected void elementClicked(int var1, boolean var2)
    {
        this.parent.selectModIndex(var1);
    }

    @Override
    protected boolean isSelected(int var1)
    {
        return this.parent.modIndexSelected(var1);
    }

    @Override
    protected void drawBackground()
    {
        this.parent.drawDefaultBackground();
    }

    @Override
    protected int getContentHeight()
    {
        return (this.getSize()) * 35 + 1;
    }

    @SuppressWarnings("static-access")
	@Override
    protected void drawSlot(int listIndex, int var2, int var3, int var4, Tessellator var5)
    {
        ModPlugin mc= Loader.mods.get(listIndex);
        
            this.parent.getFontRenderer().drawString(this.parent.getFontRenderer().trimStringToWidth(mc.getMeta().name, listWidth - 10), this.left + 3 , var3 + 2, 0xFFFFFF);
            this.parent.getFontRenderer().drawString(this.parent.getFontRenderer().trimStringToWidth(mc.getMeta().version, listWidth - 10), this.left + 3 , var3 + 12, 0xCCCCCC);
            //this.parent.getFontRenderer().drawString(this.parent.getFontRenderer().trimStringToWidth(mc.getMetadata() !=null ? mc.getMetadata().getChildModCountString() : "Metadata not found", listWidth - 10), this.left + 3 , var3 + 22, 0xCCCCCC);
        
    }

}