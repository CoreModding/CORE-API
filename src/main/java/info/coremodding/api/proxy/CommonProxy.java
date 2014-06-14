package info.coremodding.api.proxy;

import info.coremodding.api.logging.LogHelper;

import java.util.logging.Level;

import cpw.mods.fml.relauncher.Side;

/**
 * The common proxy for both sides
 */
public class CommonProxy
{

    /**
     * @return If the current instance is server or client side.
     */
    public Side getSide()
    {
        return Side.SERVER;
    }

    /**
     * Adds capes to our team Player Entities.
     */
    public void registerTeamCapes()
    {

        LogHelper.log("Server Side. Not adding capes.", Level.INFO);

    }
}
