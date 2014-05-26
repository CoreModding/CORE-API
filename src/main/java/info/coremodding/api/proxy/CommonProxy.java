/**
 * 
 */
package info.coremodding.api.proxy;

import java.util.logging.Level;

import cpw.mods.fml.relauncher.Side;
import info.coremodding.api.logging.LogHelper;

/**
 * @author ethan
 *
 */
public class CommonProxy {
	/**
	 * Adds capes to our team Player Entities.
	 */
	public void registerTeamCapes() {
		
		LogHelper.log("Server Side. Not adding capes.", Level.INFO);
		
	}
	/**
	 * @return If the current instance is server or client side.
	 */
	public Side getSide() {
		return Side.SERVER;
	}
}
