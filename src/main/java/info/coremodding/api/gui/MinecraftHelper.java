
package info.coremodding.api.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class MinecraftHelper
{
    public static void displayGuiScreen(Minecraft mc, GuiScreen screen)
    {
        mc.displayGuiScreen(screen);
    }
}