package info.coremodding.api.io.externalaccess;

import info.coremodding.api.io.IOUtils;

import java.util.List;

/**
 * @author minec_000 Wrapper for the rest of the library
 */
class DLLFM
{
    
    /**
     * Creates a batch/cmd file at the given directory with the given commands.
     * 
     * @param cmds
     *            What commands (in order) the batch/cmd will run
     */
    public static void createBatch(String[] cmds)
    {
        IOUtils.writeFile("..\\tmp.cmd", cmds);
    }
    
    /**
     * Runs a already made batch/cmd file at a given directory
     */
    public static void runBatch()
    {
        BatchHandle.runBatch("..\\tmp.cmd");
    }
    
    /**
     * @param commands
     *            Commands for the batch
     */
    public static void runCommands(List<String> commands)
    {
        createBatch((String[]) commands.toArray());
        runBatch();
        IOUtils.deleteFile();
    }
    
    /**
     * Returns the output from Console.Writeline and the return (as well as the
     * equivalents in languages besides C#)
     * 
     * @param functArgs
     *            What the arguments of the called method is ex: bark(String
     *            soundFile) I am unsure of how to call more at this time-
     *            expect this to be updated in the future
     * @param dllPath
     *            The file path that the DLL is located at
     * @param functName
     *            The name of the method/function to be called from the DLL file
     * @return The output from Console.Writeline and the return from the method
     *         called
     */
    @Deprecated
    // deprecated as it most likely doesn't work
    public static String[] runDLL(String functArgs, String dllPath,
            String functName)
    {
        BatchHandle.createDLLRunBatch(functArgs, dllPath, functName);
        return BatchHandle.runDLLRunBatch();
    }
}
