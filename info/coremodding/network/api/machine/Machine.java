package info.coremodding.network.api.machine;

import info.coremodding.network.api.meu.MEUPacket;
import info.coremodding.network.api.meu.MEUSystemManager;

import java.util.List;

import net.minecraft.tileentity.TileEntity;

/**
 * The base machine
 */
public class Machine extends TileEntity implements IMachine{
	List<MEUPacket> packets;
	
	@Override
	public int depositEnergy(MEUPacket packet) {
		this.packets.add(packet);
		this.packets = MEUSystemManager.mergePackets(this.packets);
		return packet.getAmount();
	}

	@Override
	public int removeEnergy(MEUPacket packet) {
		packet.addAmount(-packet.getAmount());
		this.packets.add(packet);
		this.packets = MEUSystemManager.mergePackets(this.packets);
		return -packet.getAmount();
	}
	
	/**
	 * @return These packets
	 */
	public List<MEUPacket> getPackets(){
		return this.packets;
	}
	
	/**
	 * @param packets The new packets
	 */
	public void setPackets(List<MEUPacket> packets){
		this.packets = packets;
	}
}
