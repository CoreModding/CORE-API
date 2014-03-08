package info.coremodding.api.io.externalaccess;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;

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
    public static void createDLLRunBatch(String args, String dllPath,
            String methodorfunct)
    {
        File f = new File("..\\tmp.cmd");
        try
        {
            if (!(f.createNewFile()))
            {
                System.out.println("There may have been an error!");
            }
            FileUtils.writeLines(
                    new File("..\\tmp.cmd"),
                    Arrays.asList(new String[] {
                            "@ECHO OFF",
                            "rundll32 " + dllPath + "," + methodorfunct + " \""
                                    + args + "\" > tmpout.txt" }));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * @param path
     *            Where the batch to be run is located
     */
    public static void runBatch(String path)
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
    public static String[] runDLLRunBatch()
    {
        try
        {
            Runtime.getRuntime().exec("cmd /c start ..\\tmp.cmd");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            return FileUtils.readLines(new File("..\\tmp.cmd")).toArray(
                    new String[] {});
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
