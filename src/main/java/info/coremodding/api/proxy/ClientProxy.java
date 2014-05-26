/**
 * 
 */
package info.coremodding.api.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;

/**
 * @author ethan
 *
 */
public class ClientProxy extends CommonProxy{
	
	/**
	 * Adds capes to our team Player Entities.	May become invalid as soon as the UUID system is fully implemented by Mojang.
	 */
	public void registerTeamCapes() {
		final String capeURL = "http://www.mccapes.com/GalleryImages6x/0c1865261d2d0247fb6b776bdc5d6730.png"; // Temporary. Can someone make us a proper cape? We should be able to have a HD one I think!
		
		String[] teamMembers = {"nxsupert, ProfessorVennie"}; // Please add your minecraft user names here. You will get a cape if you do so!

    	ThreadDownloadImageData capeImage = new ThreadDownloadImageData(capeURL, null, null);

    	for (String username : teamMembers) {
    		Minecraft.getMinecraft().renderEngine.loadTexture(new ResourceLocation("cloaks/" + username), (ITextureObject) capeImage);
    	}
	}
	
	/**
	 * @return If the current instance is server or client side.
	 */
	@Override()
	public Side getSide() {
		return Side.CLIENT;
	}
}
