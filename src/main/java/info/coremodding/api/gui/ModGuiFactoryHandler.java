package info.coremodding.api.gui;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.client.IModGuiFactory;

public class ModGuiFactoryHandler implements IModGuiFactory
{
    @Override
    public void initialize(Minecraft minecraftInstance)
    {   
        
    }
    
    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass()
    {
        return Menu.class;
    }
    
    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
    {
        return null;
    }
    
    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element)
    {
        return null;
    }
}