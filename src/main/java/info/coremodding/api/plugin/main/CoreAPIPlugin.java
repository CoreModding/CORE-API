package info.coremodding.api.plugin.main;

import info.coremodding.api.plugin.ModPlugin;
import info.coremodding.api.plugin.annotation.Info;
import info.coremodding.api.plugin.annotation.PluginMetadata;

@Info(name = "Test", version = "0.0.1")
public class CoreAPIPlugin implements ModPlugin
{
    
    private PluginMetadata meta;
    
    private ModState       state;
    
    @Override
    public PluginMetadata getMeta()
    {
        return meta;
    }
    
    @Override
    public Object getMod()
    {
        return this;
    }
    
    @Override
    public void init()
    {
        
    }
    
    @Override
    public boolean matches(Object mod)
    {
        return true;
    }
    
    @Override
    public PluginMetadata meta()
    {
        return setMeta(new PluginMetadata(CoreAPIPlugin.class));
    }
    
    @Override
    public void nextState()
    {
        
        if (this.state == null)
        {
            this.state = ModState.UNLOADED;
            return;
        }
        if (this.state.ordinal() + 1 < ModState.values().length)
        {
            this.state = ModState.values()[this.state.ordinal() + 1];
        }
    }
    
    @Override
    public void postInit()
    {
        
    }
    
    @Override
    public void preInit()
    {
        setMeta(new PluginMetadata(CoreAPIPlugin.class));
        getMeta().description = "Test";
    }
    
    @Override
    public PluginMetadata setMeta(PluginMetadata meta)
    {
        this.meta = meta;
        return meta;
    }
    
    @Override
    public void tickEnd(TickType tick, Object... data)
    {
        
    }
    
    @Override
    public void tickStart(TickType tick, Object... data)
    {
        
    }
    
    @Override
    public boolean wantsPostInit()
    {
        return true;
    }
    
    @Override
    public boolean wantsPreInit()
    {
        return true;
    }
    
}
