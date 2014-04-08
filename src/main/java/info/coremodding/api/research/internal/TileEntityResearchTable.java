package info.coremodding.api.research.internal;

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
    public void closeInventory()
    {
        
    }
    
    @Override
    public ItemStack decrStackSize(int var1, int var2)
    {
        ItemStack item = this.inv.get(var1);
        if (item.stackSize <= var2)
        {
            item = null;
        }
        else
        {
            item.stackSize -= var2;
        }
        return item;
    }
    
    @Override
    public String getInventoryName()
    {
        return "Research Table";
    }
    
    @Override
    public int getInventoryStackLimit()
    {
        return 5;
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
    public ItemStack getStackInSlotOnClosing(int var1)
    {
        return this.inv.get(var1);
    }
    
    @Override
    public boolean hasCustomInventoryName()
    {
        return true;
    }
    
    @Override
    public boolean isItemValidForSlot(int var1, ItemStack var2)
    {
        return true;
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
    public void setInventorySlotContents(int var1, ItemStack var2)
    {
        this.inv.set(var1, var2);
    }
    
    @Override
    public void updateEntity()
    {
        
    }
}
