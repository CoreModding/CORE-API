package info.coremodding.api.io.externalaccess;

import info.coremodding.api.io.IOUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author minec_000
 */
class BatchHandle
{
    
    /**
     * @param args
     *            The arguments for the method to be called
     * @param dllPath
     *            The location of the DLL to be called
     * @param methodorfunct
     *            The function or method to be called from the DLL
     */
    static void createDLLRunBatch(String args, String dllPath,
            String methodorfunct)
    {
        File f = new File("C:\\tmpcmd.cmd");
        try
        {
            if (!(f.createNewFile()))
            {
                System.out.println("There may have been an error!");
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        IOUtils.writeFile("C:\\tmpcmd.cmd", new String[] {
                "@ECHO OFF",
                "rundll32 " + dllPath + "," + methodorfunct + " \"" + args
                        + "\" > tmpout.txt" });
    }
    
    /**
     * @param path
     *            Where the batch to be run is located
     */
    static void runBatch(String path)
    {
        try
        {
            Runtime.getRuntime().exec("cmd /c start " + path);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * @return Returns the output of the writeline and return value of the DLL
     *         method being called via batch
     */
    static String[] runDLLRunBatch()
    {
        try
        {
            Runtime.getRuntime().exec("cmd /c start C:/tmpcmd.cmd");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return IOUtils.readFile("C:\\tmpout.txt");
    }
}
