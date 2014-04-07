package info.coremodding.api.plugin.common;

import info.coremodding.api.plugin.Loader;
import cpw.mods.fml.common.IFMLSidedHandler;

public class CAPCommonHandler
{
    /**
     * The singleton
     */
    private static final CAPCommonHandler INSTANCE = new CAPCommonHandler();
    /**
     * We register our delegate here
     * @param handler
     */
    public void registerSidedDelegate(IFMLSidedHandler handler)
    {
    }

    /**
     * @return the instance
     */
    public static CAPCommonHandler instance()
    {
        return INSTANCE;
    }

    /**
     * Is this a modloader mod?
     * @param clazz
     * @return
     */
    public boolean isModLoaderMod(Class<?> clazz)
    {
        return Loader.mods.contains(clazz);
    }
}