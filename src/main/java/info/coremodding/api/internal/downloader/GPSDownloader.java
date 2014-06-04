package info.coremodding.api.internal.downloader;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

/**
 * @author James
 *         Automatically downloads or updates GPS when core-api is installed so
 *         people only need to download one thing
 */
public class GPSDownloader
{
    
    /**
     * Does the update
     */
    public static void doUpdate()
    {
        try
        {
            for (File file : new File("." + File.separatorChar).listFiles())
            {
                if (file.getName().startsWith("GPS")) file.delete();
            }
            FileUtils.copyURLToFile(new URL("https://github.com/CoreModding/GodlyEnergySystems/raw/master/dls/APIDist1_7.jar"), new File("." + File.separatorChar +"GPS_Automatic.jar"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
