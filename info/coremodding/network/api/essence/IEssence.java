package info.coremodding.network.api.essence;

/**
 * All essences implement this
 */
public interface IEssence {

	/**
	 * @return The name of the essence
	 */
	public String getName();
	
	/**
	 * @return The hex color of the essence
	 */
	public String getColor();
}
