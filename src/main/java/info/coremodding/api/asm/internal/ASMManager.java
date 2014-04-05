package info.coremodding.api.asm.internal;

import info.coremodding.api.asm.ASMItem;

import java.util.ArrayList;

/**
 * @author James
 *         Manages ASM stuff for Core-API
 */
public class ASMManager
{
    
    /**
     * All ASM items
     */
    public static ArrayList<ASMItem> items = new ArrayList<>();
    
    /**
     * @param item
     *            The item to register
     */
    public static void registerItem(ASMItem item)
    {
        items.add(item);
    }
}
