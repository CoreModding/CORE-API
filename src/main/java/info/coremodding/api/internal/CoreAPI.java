package info.coremodding.api.internal;

import info.coremodding.api.research.internal.BlockResearchTable;
import info.coremodding.api.research.internal.TileEntityResearchTable;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * The main mod class
 */
@Mod(modid = CoreAPI.ModID, name = CoreAPI.ModName, version = CoreAPI.ModVer)
public class CoreAPI
{
    
    /**
     * The instance of this mod forge uses
     */
    @Instance(CoreAPI.ModID)
    public static final CoreAPI instance = null;
    
    /**
     * The mod ID
     */
    public static final String  ModID    = "CoreModding_LIB";
    
    /**
     * The mod name
     */
    public static final String  ModName  = "CoreModding API";
    
    /**
     * The mod version
     */
    public static final String  ModVer   = "0.0.1";
    
    /**
     * Should the research API be enabled
     */
    public static boolean       EnableResearch;
    
    /**
     * Enables the research API
     * 
     * @param evt
     *            Forces you to do this in pre-init as the research module is
     *            created in init.
     */
    public static void enableResearch(FMLPreInitializationEvent evt)
    {
        EnableResearch = true;
    }
    
    /**
     * @param evt
     *            The event that triggered the method
     */
    @SuppressWarnings("static-method")
    @EventHandler
    public void init(FMLInitializationEvent evt)
    {
        if (EnableResearch)
        {
            System.out.println("Enabling research module for Core-API.");
            GameRegistry.registerBlock(new BlockResearchTable(),
                    "Research Table");
            GameRegistry.registerTileEntity(TileEntityResearchTable.class,
                    "CAPI_Research_Table");
        }
    }
}
