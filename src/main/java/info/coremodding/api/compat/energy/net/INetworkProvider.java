package info.coremodding.api.compat.energy.net;

/**
 * Applied to TileEntities that has an instance of an electricity network.
 * 
 */
public interface INetworkProvider<N>
{
	public N getNetwork();

	public void setNetwork(N network);
}
