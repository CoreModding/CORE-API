package info.coremodding.api.debug;

/**
 * @author James
 *         Shows a list of all system properties
 */
public class ShowProperties
{
    
    /**
     * @param args
     *            The command line arguments
     */
    public static void main(String[] args)
    {
        System.getProperties().list(System.out);
    }
}
