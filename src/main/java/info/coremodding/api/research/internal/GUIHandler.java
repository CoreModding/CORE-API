package info.coremodding.api.research.internal;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

/**
 * @author James
 *         The handler for the GUIs
 */
public class GUIHandler implements IGuiHandler
{
    
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityResearchTable) { return new GUIResearchTable(player.inventory, (TileEntityResearchTable) tileEntity, new ContainerResearchTable(player.inventory, (TileEntityResearchTable) tileEntity)); }
        return null;
    }
    
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityResearchTable) { return new ContainerResearchTable(player.inventory, (TileEntityResearchTable) tileEntity); }
        return null;
    }
}
