package info.coremodding.api.internal.research;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @author James
 *         The container for the research table
 */
public class ContainerResearchTable extends Container
{
    
    @SuppressWarnings("unused")
    private final TileEntityResearchTable te;
    
    /**
     * @param inventory
     *            The inventory of the player who opened the research table
     * @param tileEntity
     *            The tile entity to open with
     */
    public ContainerResearchTable(InventoryPlayer inventory, TileEntityResearchTable tileEntity)
    {
        this.te = tileEntity;
        this.bindPlayerInventory(inventory);
    }
    
    /**
     * Binds the player's inventory to the tanner container
     * 
     * @param inventoryPlayer
     *            The inventory to be bound
     */
    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++)
        {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer var1)
    {
        return true;
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot)
    {
        ItemStack stack = null;
        Slot slotObject = (Slot) this.inventorySlots.get(slot);
        
        if (slotObject != null && slotObject.getHasStack())
        {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();
            
            if (slot < 9) if (!this.mergeItemStack(stackInSlot, 0, 35, true)) return null;
            else if (!this.mergeItemStack(stackInSlot, 0, 9, false)) return null;
            
            if (stackInSlot.stackSize == 0) slotObject.putStack(null);
            else slotObject.onSlotChanged();
            
            if (stackInSlot.stackSize == stack.stackSize) return null;
            
            slotObject.onPickupFromSlot(player, stackInSlot);
        }
        return stack;
    }
}
