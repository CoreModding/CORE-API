package info.coremodding.api.internal.research;

import info.coremodding.api.internal.CoreAPI;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

/**
 * @author James
 *         The research table block class
 */
public class BlockResearchTable extends BlockContainer
{
    
    /**
     * The constructor
     */
    public BlockResearchTable()
    {
        super(Material.wood);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setBlockName("Research Table");
        this.setHardness(5.0F);
        this.setLightLevel(10);
        this.setResistance(3.0F);
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new TileEntityResearchTable();
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int metadata, float what, float these,
            float are)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity == null || player.isSneaking()) { return false; }
        FMLNetworkHandler.openGui(player, CoreAPI.instance, 0, world, x, y, z);
        return true;
    }
}
