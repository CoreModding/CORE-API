package info.coremodding.api.modloader;

/**
 * @author James All mods have this
 */
public @interface Mod {

	/**
	 * @return The loaded mod's name
	 */
	public String name();

	/**
	 * @return The loaded mod's version (no current effect)
	 */
	public String version();
}
