package info.coremodding.network.api.meu;

/**
 * The base for most packets
 */
public abstract class MEUPacketBase implements MEUPacket {

	int amount = 0;
	
	@Override
	public int getAmount() {
		return this.amount;
	}

	@Override
	public int addAmount(int add) {
		this.amount += add;
		return getAmount();
	}
}
