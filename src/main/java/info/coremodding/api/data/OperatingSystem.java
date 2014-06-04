package info.coremodding.api.data;

/**
 * @author James
 *         Operating system information
 */
public enum OperatingSystem
{
    
    /**
     * The windows operating system
     */
    Windows,
    
    /**
     * The mac operating system
     */
    Mac,
    
    /**
     * The linux operating system
     */
    Linux,
    
    /**
     * A different unix than linux
     */
    Unix,
    
    /**
     * Some other operating system
     */
    Other;
    
    /**
     * THIS MOST LIKELY WILL NOT WORK. I HAVE NOT TESTED IT. I ONLY KNOW FOR
     * SURE WINDOWS WORKS. SO BEWARE.
     * 
     * @return The user's operating system
     */
    public static OperatingSystem getCurrentSystem()
    {
        String os = System.getProperty("os.name");
        return os.startsWith("Windows") ? Windows : os.startsWith("Mac") ? Mac : os.startsWith("Linux") ? Linux : os.startsWith("Unix") ? Unix : Other;
    }
    
    /**
     * @return The user's operating system name
     */
    public static String getCurrentSystemName()
    {
        return System.getProperty("os.name");
    }
}
