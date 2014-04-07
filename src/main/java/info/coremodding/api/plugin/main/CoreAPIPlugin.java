package info.coremodding.api.plugin.main;

import info.coremodding.api.plugin.ModPlugin;
import info.coremodding.api.plugin.PluginMetadata;
import info.coremodding.api.plugin.common.Info;

import java.io.File;
import java.util.List;
@Info(name = "Test", version = "0.0.1")
public class CoreAPIPlugin implements ModPlugin{

	private PluginMetadata meta;
	
	private ModState state;

	@Override
	public boolean wantsPreInit() 
	{
		return true;
	}

	@Override
	public boolean wantsPostInit() 
	{
		return true;
	}

	@Override
	public void preInit() 
	{
		setMeta(new PluginMetadata(CoreAPIPlugin.class));
		getMeta().description="Test";
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
		return null;
	}

	@Override
	public void nextState() {
		
		if (state==null) {
            state=ModState.UNLOADED;
            return;
        }
        if (state.ordinal()+1<ModState.values().length) {
            state=ModState.values()[state.ordinal()+1];
        }
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
		return getSource();
	}

	@Override
	public String getSortingRules() 
	{
		return "*";
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

	@Override
	public PluginMetadata meta()
	{
		return setMeta(new PluginMetadata(CoreAPIPlugin.class));
	}

	public PluginMetadata getMeta() 
	{
		return meta;
	}

	public PluginMetadata setMeta(PluginMetadata meta) 
	{
		this.meta = meta;
		return meta;
	}


}
