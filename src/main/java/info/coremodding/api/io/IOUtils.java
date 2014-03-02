package info.coremodding.api.io;

import org.apache.commons.lang3.ArrayUtils;

import java.io.*;

/**
 * @author minec_000
 *         <p>
 *         IO stuff
 */
public class IOUtils {

    /**
     * @param path The path of the file to delete
     */
    public static void deleteFile(String path) {
        if(!new File(path).delete()){
            System.out.println("There may have been an error!");
        }
    }

    /**
     * @param path Where the file to be read is
     * @return The file lines in data
     */
    @SuppressWarnings("resource")
    public static String[] readFile(String path) {
        String[] returnable = new String[]{};
        try {
            File f = new File(path);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                returnable = ArrayUtils.addAll(returnable, sCurrentLine);
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return returnable;
    }

    /**
     * @param path  The path of the file to be written to
     * @param Input What to be written into the file
     */
    @SuppressWarnings("resource")
    public static void writeFile(String path, String Input) {
        File f = new File(path);
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Input);
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param path  Where the file will be written to
     * @param Lines The lines to be written into the file
     */
    @SuppressWarnings("resource")
    public static void writeFile(String path, String[] Lines) {
        File f = new File(path);
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String Line : Lines) {
                bw.write(Line);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
