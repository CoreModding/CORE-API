package info.coremodding.api.plugin;

import info.coremodding.api.plugin.common.Info;

import java.io.File;
import java.util.List;

public class CAPluginContainer implements ModPlugin
{
    
    public static ModPlugin buildFor(Class<?> clazz)
    {
        return new CAPluginContainer(clazz);
    }
    public Info            modDescriptor;
    public Object          modInstance;
    private File           source;
    private PluginMetadata meta;
    
    private ModState       state;
    
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
    
    public CAPluginContainer(String dummy)
    {
        this(new File(dummy));
    }
    
    @Override
    public List<String> getDependencies()
    {
        return null;
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
    public ModState getModState()
    {
        return this.state;
    }
    
    @Override
    public List<String> getPostDepends()
    {
        return null;
    }
    
    @Override
    public List<String> getPreDepends()
    {
        return null;
    }
    
    @Override
    public String getSortingRules()
    {
        return null;
    }
    
    @Override
    public File getSource()
    {
        return this.source;
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
