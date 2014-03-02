package info.coremodding.api.net.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

/**
 * @author James
 */
class DownloadUtil {

    /**
     * @param urlString The URL
     * @return The URL data
     */
    @SuppressWarnings("boxing")
    public static HashMap<Integer, String> saveUrl(String urlString) {
        try {
            HashMap<Integer, String> data = new HashMap<>();
            URL url = new URL(urlString);
            @SuppressWarnings("resource")
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    url.openStream()));

            String inputLine;
            int i = 0;
            while ((inputLine = in.readLine()) != null) {
                data.put(i, inputLine);
                i++;
            }
            in.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
