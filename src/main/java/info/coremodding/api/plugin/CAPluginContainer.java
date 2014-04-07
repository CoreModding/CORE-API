package info.coremodding.api.plugin;

import info.coremodding.api.plugin.common.Info;

import java.io.File;
import java.util.List;

public class CAPluginContainer implements ModPlugin
{
    
    public Info            modDescriptor;
    public Object          modInstance;
    private File           source;
    private PluginMetadata meta;
    private ModState       state;
    
    public CAPluginContainer(String dummy)
    {
        this(new File(dummy));
    }
    
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
        this.source = source;
    }
    
    @Override
    public PluginMetadata meta()
    {
        return this.meta;
    }
    
    @Override
    public PluginMetadata getMeta()
    {
        return null;
    }
    
    @Override
    public PluginMetadata setMeta(PluginMetadata meta)
    {
        return null;
    }
    
    @Override
    public boolean wantsPreInit()
    {
        return false;
    }
    
    @Override
    public boolean wantsPostInit()
    {
        return false;
    }
    
    @Override
    public void preInit()
    {
        
    }
    
    @Override
    public void init()
    {
        
    }
    
    @Override
    public void postInit()
    {
        
    }
    
    @Override
    public ModState getModState()
    {
        return this.state;
    }
    
    @Override
    public void nextState()
    {
        
    }
    
    @Override
    public void tickStart(TickType tick, Object... data)
    {
        
    }
    
    @Override
    public void tickEnd(TickType tick, Object... data)
    {
        
    }
    
    @Override
    public boolean matches(Object mod)
    {
        return true;
    }
    
    @Override
    public File getSource()
    {
        return this.source;
    }
    
    @Override
    public String getSortingRules()
    {
        return null;
    }
    
    @Override
    public Object getMod()
    {
        return this;
    }
    
    @Override
    public List<String> getDependencies()
    {
        return null;
    }
    
    @Override
    public List<String> getPreDepends()
    {
        return null;
    }
    
    @Override
    public List<String> getPostDepends()
    {
        return null;
    }
    
    public static ModPlugin buildFor(Class<?> clazz)
    {
        return new CAPluginContainer(clazz);
    }
    
}
