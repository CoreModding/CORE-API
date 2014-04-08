package info.coremodding.api.plugin;

import info.coremodding.api.plugin.annotation.PluginMetadata;

/**
 * @author RoboRAve
 * 
 **/

public interface ModPlugin
{
    
    public enum ModState
    {
        UNLOADED("Unloaded"), LOADED("Loaded"), PREINITIALIZED(
                "Pre-initialized"), INITIALIZED("Initialized"),
        POSTINITIALIZED("Post-initialized"), AVAILABLE("Available");
        private String label;
        
        private ModState(String label)
        {
            this.label = label;
        }
        
        @Override
        public String toString()
        {
            return this.label;
        }
    }
    
    public enum TickType
    {
        WORLD, RENDER, GUI, WORLDGUI;
    }
    
    PluginMetadata getMeta();
    
    /**
     * The actual mod object itself
     * 
     * @return
     */
    Object getMod();
    
    /**
     * Called when main initialization occurs.
     */
    void init();
    
    /**
     * Does this mod match the supplied mod?
     * 
     * @param mod
     * @return
     */
    boolean matches(Object mod);
    
    PluginMetadata meta();
    
    /**
     * Move to the next mod state
     */
    void nextState();
    
    /**
     * Called when post-initialization occurs.
     */
    void postInit();
    
    /**
     * Called when pre-initialization occurs.
     */
    void preInit();
    
    PluginMetadata setMeta(PluginMetadata meta);
    
    /**
     * Does this mod want to generate world data.
     * 
     * @return
     */
    
    /**
     * A tick has ended
     */
    void tickEnd(TickType tick, Object... data);
    
    /**
     * A tick has started
     */
    void tickStart(TickType tick, Object... data);
    
    /**
     * The enclosed Plugin wants to be called during post-initialization.
     * 
     * @return
     */
    boolean wantsPostInit();
    
    /**
     * The enclosed Plugin wants to be called during pre-initialization.
     * 
     * @return
     */
    boolean wantsPreInit();
    
}