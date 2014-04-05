package info.coremodding.api.asm.internal;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;

/**
 * @author James
 *         The core api main class for the ASM module
 */
public class CoreAPIAsm extends DummyModContainer
{
    
    /**
     * The class constructor
     */
    public CoreAPIAsm()
    {
        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId = "coreapi_asm";
        meta.name = "Core-API ASM";
        meta.version = "@VERSION@";
        meta.credits = "The Core Modding Team";
        meta.authorList = Arrays.asList(new String[0]);
        meta.description = "";
        meta.url = "coremodding.info";
        meta.updateUrl = "coremodding.info";
        meta.screenshots = new String[0];
        meta.logoFile = "";
    }
    
    @Override
    public boolean registerBus(EventBus bus, LoadController controller)
    {
        return false;
    }
}
