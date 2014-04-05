package info.coremodding.api.internal.research;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * @author James
 *         The research table tile entity
 */
public class TileEntityResearchTable extends TileEntity implements IInventory
{
    
    private ArrayList<ItemStack> inv;
    
    @Override
    public void updateEntity()
    {
        
    }
    
    @Override
    public int getSizeInventory()
    {
        return (this.inv.size());
    }
    
    @Override
    public ItemStack getStackInSlot(int var1)
    {
        return this.inv.get(var1);
    }
    
    @Override
    public ItemStack decrStackSize(int var1, int var2)
    {
        ItemStack item = this.inv.get(var1);
        if (item.stackSize <= var2)
        {
            item = null;
        } else
        {
            item.stackSize -= var2;
        }
        return item;
    }
    
    @Override
    public ItemStack getStackInSlotOnClosing(int var1)
    {
        return this.inv.get(var1);
    }
    
    @Override
    public void setInventorySlotContents(int var1, ItemStack var2)
    {
        this.inv.set(var1, var2);
    }
    
    @Override
    public String getInventoryName()
    {
        return "Research Table";
    }
    
    @Override
    public boolean hasCustomInventoryName()
    {
        return true;
    }
    
    @Override
    public int getInventoryStackLimit()
    {
        return 5;
    }
    
    @Override
    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return true;
    }
    
    @Override
    public void openInventory()
    {
        
    }
    
    @Override
    public void closeInventory()
    {
        
    }
    
    @Override
    public boolean isItemValidForSlot(int var1, ItemStack var2)
    {
        return true;
    }
}
