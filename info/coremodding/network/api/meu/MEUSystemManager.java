package info.coremodding.network.api.meu;

import info.coremodding.api.locations.Location;
import info.coremodding.network.api.machine.IMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The power manager for the mod
 */
public class MEUSystemManager {

	/**
	 * @param loc The location
	 * @return Touching machines
	 */
	public static List<IMachine> getTouching(Location loc){
		if(loc.getWorld() != null){
			ArrayList<IMachine> touching = new ArrayList<>();
			if(loc.getWorld().getTileEntity(loc.getX(), loc.getY(), loc.getZ()) instanceof IMachine) touching.add((IMachine) loc.getWorld().getTileEntity(loc.getX() + 1, loc.getY(), loc.getZ()));
			if(loc.getWorld().getTileEntity(loc.getX(), loc.getY(), loc.getZ()) instanceof IMachine) touching.add((IMachine) loc.getWorld().getTileEntity(loc.getX() - 1, loc.getY(), loc.getZ()));
			if(loc.getWorld().getTileEntity(loc.getX(), loc.getY(), loc.getZ()) instanceof IMachine) touching.add((IMachine) loc.getWorld().getTileEntity(loc.getX(), loc.getY() + 1, loc.getZ()));
			if(loc.getWorld().getTileEntity(loc.getX(), loc.getY(), loc.getZ()) instanceof IMachine) touching.add((IMachine) loc.getWorld().getTileEntity(loc.getX(), loc.getY() - 1, loc.getZ()));
			if(loc.getWorld().getTileEntity(loc.getX(), loc.getY(), loc.getZ()) instanceof IMachine) touching.add((IMachine) loc.getWorld().getTileEntity(loc.getX(), loc.getY(), loc.getZ() + 1));
			if(loc.getWorld().getTileEntity(loc.getX(), loc.getY(), loc.getZ()) instanceof IMachine) touching.add((IMachine) loc.getWorld().getTileEntity(loc.getX(), loc.getY(), loc.getZ() - 1));
			return touching;		
		}
		return null;
	}
	
	/**
	 * @param loc The location
	 * @return Gets the machine at the given location
	 */
	public static IMachine getAt(Location loc){
		if(loc.getWorld() != null)
			if(loc.getWorld().getTileEntity(loc.getX(), loc.getY(), loc.getZ()) instanceof IMachine) return((IMachine) loc.getWorld().getTileEntity(loc.getX(), loc.getY(), loc.getZ()));
		return null;
	}
	
	/**
	 * @param _packets The packets to merge
	 * @return The packets merged
	 */
	public static List<MEUPacket> mergePackets(List<MEUPacket> _packets){
		HashMap<String, MEUPacket> packets = new HashMap<>();
		for(MEUPacket packet1 : _packets){
			if(packets.get(packet1.getName()) == null)
				packets.put(packet1.getName(), packet1);
			else {
				MEUPacket old = packets.get(packet1.getName());
				MEUPacket _new = packet1;
				_new.addAmount(old.getAmount());
				packets.replace(_new.getName(), _new);
			}
		}
		ArrayList<MEUPacket> returnable = new ArrayList<>();
		for(MEUPacket packet : packets.values()){
			returnable.add(packet);
		}
		return returnable;
	}
}
