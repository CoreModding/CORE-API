package info.coremodding.api.asm;

import info.coremodding.api.asm.internal.ASMManager;

import org.objectweb.asm.tree.AbstractInsnNode;

/**
 * @author James
 *         An ASM modification item
 */
public class ASMItem
{
    
    /**
     * The obfuscated class name
     */
    public String             obfuscatedClass;
    
    /**
     * The normal class name
     */
    public String             workspaceClass;
    
    /**
     * The obfuscated method name
     */
    public String             obfuscatedMethod;
    
    /**
     * The normal method name
     */
    public String             workspaceMethod;
    
    /**
     * The description of the method to patch
     */
    public String             itemDescription;
    
    /**
     * The instruction to start patching at
     */
    public int                startInstruction;
    
    /**
     * The number of instructions to remove
     */
    public int                removeNumber;
    
    /**
     * The instructions to insert
     */
    public AbstractInsnNode[] newInstructions;
    
    private final String      submitMod;
    
    /**
     * @param obfclass
     *            The obfuscated class name
     * @param wrkclass
     *            The .getName() of the workspace class
     * @param obfmethod
     *            The obfuscated method name
     * @param wrkmethod
     *            The workspace method name
     * @param desc
     *            The description of the method to patch
     * @param startInst
     *            The instruction to start patching at
     * @param remNum
     *            The number of instructions to remove including the given
     *            instruction
     * @param newInsts
     *            The instructions to insert
     * @param modname
     *            The name of the mod that registered this for debug
     */
    public ASMItem(String obfclass, String wrkclass, String obfmethod,
            String wrkmethod, String desc, int startInst, int remNum,
            AbstractInsnNode[] newInsts, String modname)
    {
        this.obfuscatedClass = obfclass;
        this.workspaceClass = wrkclass;
        this.obfuscatedMethod = obfmethod;
        this.workspaceMethod = wrkmethod;
        this.itemDescription = desc;
        this.startInstruction = startInst;
        this.removeNumber = remNum;
        this.newInstructions = newInsts;
        this.submitMod = modname;
        ASMManager.registerItem(this);
    }
    
    @Override
    public String toString()
    {
        return "MOD ERROR: " + this.submitMod + " WHILE PATCHING "
                + this.workspaceClass + " M" + this.workspaceMethod;
    }
}
