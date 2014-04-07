package info.coremodding.api.plugin;

import java.io.File;
import java.util.List;

/**
 * @author RoboRAve
 *
 **/

public interface ModPlugin
{
	
    public enum ModState {
        UNLOADED("Unloaded"), LOADED("Loaded"), PREINITIALIZED("Pre-initialized"), INITIALIZED("Initialized"), POSTINITIALIZED("Post-initialized"), AVAILABLE("Available");
        private String label;

        private ModState(String label) {
            this.label=label;
        }
        public String toString() {
            return this.label;
        }
    }

   
    
    PluginMetadata meta();
    
    PluginMetadata getMeta();
    
    PluginMetadata setMeta(PluginMetadata meta);
    /**
     * The enclosed Plugin wants to be called during pre-initialization.
     * @return
     */
    boolean wantsPreInit();
    /**
     * The enclosed Plugin wants to be called during post-initialization.
     * @return
     */
    boolean wantsPostInit();
    /**
     * Called when pre-initialization occurs.
     */
    void preInit();
    /**
     * Called when main initialization occurs.
     */
    void init();
    /**
     * Called when post-initialization occurs.
     */
    void postInit();
    /**
     * The state of the mod
     * @return
     */
    ModState getModState();
    /**
     * Move to the next mod state
     */
    void nextState();
    /**
     * A tick has started
     */
    void tickStart(TickType tick, Object ... data);
    /**
     * A tick has ended
     */
    void tickEnd(TickType tick, Object ... data);
    /**
     * Does this mod match the supplied mod?
     * @param mod
     * @return
     */
    boolean matches(Object mod);
    /**
     * The source of this mod: the file on the file system
     * @return
     */
    File getSource();
    /**
     * Returns the sorting rules as a string for printing
     * @return
     */
    String getSortingRules();
    /**
     * The actual mod object itself
     * @return
     */
    Object getMod();
    /**
     * Does this mod want to generate world data.
     * @return
     */
    
    /**
     * The strong dependencies of this mod. If the named mods in this list are not present, the game will abort.
     * @return
     */
    List<String> getDependencies();
    /**
     * Get a list of mods to load before this one. The special value "*" indicates to load <i>after</i> all other mods (except other "*" mods).
     * @return
     */
    List<String> getPreDepends();
    /**
     * Get a list of mods to load after this one. The special value "*" indicates to load <i>before</i> all other mods (except other "*" mods).
     * @return
     */
    List<String> getPostDepends();
    
    public enum TickType {
        WORLD, RENDER, GUI, WORLDGUI;
    }
	

}