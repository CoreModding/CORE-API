package info.coremodding.api.internal.asm;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

/**
 * @author James
 *         The FML loading plugin for Core-API
 */
public class CoreAPIFMLLoadingPlugin implements IFMLLoadingPlugin
{
    
    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }
    
    @Override
    public String[] getASMTransformerClass()
    {
        return new String[] { CoreAPIClassTransformer.class.getName() };
    }
    
    @Override
    public String getModContainerClass()
    {
        return CoreAPIAsm.class.getName();
    }
    
    @Override
    public String getSetupClass()
    {
        return null;
    }
    
    @Override
    public void injectData(Map<String, Object> data)
    {
        return;
    }
    
}
