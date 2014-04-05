package info.coremodding.api.asm.internal;

import info.coremodding.api.asm.ASMItem;

import java.util.Iterator;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * @author James
 *         The core API class transformer
 */
public class CoreAPIClassTransformer implements IClassTransformer
{
    
    @Override
    public byte[] transform(String name, String transformedName,
            byte[] basicClass)
    {
        for (ASMItem item : ASMItem.items)
        {
            if (item.obfuscatedClass.equals(name)) return patchClassASM(name,
                    basicClass, item, false);
            if (item.workspaceClass.equals(name)) return patchClassASM(name,
                    basicClass, item, true);
        }
        return basicClass;
    }
    
    /**
     * Patches an ASM class
     * 
     * @param name
     *            The name of the class
     * @param bytes
     *            The bytes of the class we're patching
     * @param item
     *            The ASM patching item
     * @param inWorkspace
     *            Is the class being handled in the workspace or is it
     *            obfuscated?
     * @return The new class after patching
     */
    public static byte[] patchClassASM(String name, byte[] bytes, ASMItem item,
            boolean inWorkspace)
    {
        
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, 0);
        
        // Loops over all methods in class until the correct method is found
        Iterator<MethodNode> methods = classNode.methods.iterator();
        while (methods.hasNext())
        {
            MethodNode m = methods.next();
            int fdiv_index = -1;
            
            // Checks if method is the one we want to patch
            if ((m.name.equals(inWorkspace ? item.workspaceMethod
                    : item.obfuscatedMethod) && m.desc
                    .equals(item.itemDescription)))
            {
                AbstractInsnNode currentNode = null;
                AbstractInsnNode targetNode = null;
                
                Iterator<AbstractInsnNode> iter = m.instructions.iterator();
                
                int index = -1;
                
                // Loop over the instruction set and find the instruction
                while (iter.hasNext())
                {
                    index++;
                    currentNode = iter.next();
                    
                    // Found the instruction
                    if (currentNode.getOpcode() == item.startInstruction)
                    {
                        targetNode = currentNode;
                        fdiv_index = index;
                    }
                }
                
                if (targetNode == null)
                {
                    System.out.println("ERROR: NODE NOT FOUND");
                    System.out.println(item.toString());
                }
                
                for (int i = 0; i < item.removeNumber; i++)
                {
                    m.instructions.remove(m.instructions.get(fdiv_index));
                }
                
                for (int i = 0; i < item.newInstructions.length; i++)
                {
                    m.instructions.insert(targetNode, item.newInstructions[i]);
                }
                break;
            }
        }
        
        // ASM specific for cleaning up and returning the final bytes for JVM
        // processing.
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS
                | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);
        return writer.toByteArray();
    }
}
