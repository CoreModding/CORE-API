package info.coremodding.api.plugin.common;

import info.coremodding.api.plugin.ModPlugin;
import info.coremodding.api.plugin.annotation.Info;
import info.coremodding.api.plugin.annotation.PluginMetadata;

import java.io.File;

public class CAPluginContainer implements ModPlugin
{
    
    public static ModPlugin buildFor(Class<?> clazz)
    {
        return new CAPluginContainer(clazz);
    }
    
    public Info            modDescriptor;
    public Object          modInstance;
    private PluginMetadata meta;
    
    public CAPluginContainer(Class<?> clazz)
    {
        
        if (clazz == null) { return; }
        
        this.modDescriptor = clazz.getAnnotation(Info.class);
        
        try
        {
            this.modInstance = clazz.newInstance();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    
    public CAPluginContainer(File source)
    {
    }
    
    public CAPluginContainer(String dummy)
    {
        this(new File(dummy));
    }
    
    @Override
    public PluginMetadata getMeta()
    {
        return null;
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
        return this.meta;
    }
    
    @Override
    public void nextState()
    {
        
    }
    
    @Override
    public void postInit()
    {
        
    }
    
    @Override
    public void preInit()
    {
        
    }
    
    @Override
    public PluginMetadata setMeta(PluginMetadata meta)
    {
        return null;
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
        return false;
    }
    
    @Override
    public boolean wantsPreInit()
    {
        return false;
    }
    
}
