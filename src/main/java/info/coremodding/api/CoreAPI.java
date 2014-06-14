package info.coremodding.api;

import info.coremodding.api.handlers.SubscribeEventHandler;
import info.coremodding.api.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

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
    public static CoreAPI instance;

    /**
     * The mod ID
     */
    public static final String ModID = "CoreAPI";

    /**
     * The mod name
     */
    public static final String ModName = "CoreModding API";

    /**
     * The mod version
     */
    public static final String ModVer = "0.0.3.1";

    /**
     * The mod Proxy.
     */
    @SidedProxy(clientSide = "info.coremodding.api.proxy.ClientProxy", serverSide = "info.coremodding.api.proxy.CommonProxy", modId = CoreAPI.ModID)
    public static CommonProxy proxy;

    /**
     * @param evt
     *            The event that triggered the method
     */
    @SuppressWarnings("static-method")
    @EventHandler
    public void init(FMLInitializationEvent evt)
    {
        MinecraftForge.EVENT_BUS.register(new SubscribeEventHandler());
        proxy.registerTeamCapes();
    }
}
