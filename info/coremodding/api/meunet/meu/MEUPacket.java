package info.coremodding.api.meunet.meu;

/**
 * All MEU packets implement this
 */
public interface MEUPacket {

	/**
	 * @return The name of the packet
	 */
	public String getName();

	/**
	 * @return The amount the packet contains
	 */
	public int getAmount();

	/**
	 * @param add
	 *            How much to add
	 * @return The new amount
	 */
	public int addAmount(int add);
}
