package info.coremodding.api.meunet.meu;

/**
 * The base for most packets
 */
public abstract class MEUPacketBase implements MEUPacket {

	int amount = 0;

	@Override
	public int addAmount(int add) {
		this.amount += add;
		return getAmount();
	}

	@Override
	public int getAmount() {
		return this.amount;
	}
}
