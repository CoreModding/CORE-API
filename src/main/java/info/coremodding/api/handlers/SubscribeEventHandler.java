package info.coremodding.api.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * @author MushroomLT
 *         Forge event handler
 */
@SuppressWarnings("static-method")
public class SubscribeEventHandler
{
    /**
     * Registers Extended Player Handler for all players
     * 
     * @param event
     *            Event that executed this method
     */
    @SubscribeEvent
    public void onEntityConstructing(EntityConstructing event)
    {
        if (event.entity instanceof EntityPlayer && ExtendedPlayerHandler.getProperties((EntityPlayer) event.entity) == null) ExtendedPlayerHandler.register((EntityPlayer) event.entity);
    }
}
