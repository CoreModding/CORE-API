package info.coremodding.api.research.internal;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * @author James
 *         The GUI for the research table
 */
public class GUIResearchTable extends GuiContainer
{
    
    /**
     * @param inventory
     *            The player inventory
     * @param tileEntity
     *            The tile entity
     * @param container
     *            The container
     */
    public GUIResearchTable(InventoryPlayer inventory, TileEntityResearchTable tileEntity, ContainerResearchTable container)
    {
        super(container);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        
    }
}
