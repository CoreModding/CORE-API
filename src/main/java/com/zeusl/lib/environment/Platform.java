package com.zeusl.lib.environment;

/**
 * @author Zeus
 *         The platform that the user is running on
 */
public class Platform
{

    /**
     * The computer's processor
     */
    public static String osArch = System.getProperty("os.arch");

    /**
     * The name of the operating system being run
     */
    public static String osName = System.getProperty("os.name").toLowerCase();

    /**
     * The version of the operating system being run
     */
    public static String osVersion = System.getProperty("os.version")
            .toLowerCase();

    /**
     * Checks if processor is 64 bit.
     * WARNING: UNRELIABLE
     * 
     * @return The processor bits
     */
    public static boolean is64Bit()
    {
        return osArch.contains("64");
    }

    /**
     * @return Does the user run linux or unix
     */
    public static boolean isLinux()
    {
        return (osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0 || osName
                .indexOf("aix") > 0);
    }

    /**
     * @return Is the user fucking retarded
     */
    public static boolean isMac()
    {
        return (osName.indexOf("mac") >= 0);
    }

    /**
     * @return Is the user running something else
     */
    public static boolean isOther()
    {
        return !(isWindows() || isMac() || isLinux() || isSolaris());
    }

    /**
     * @return Does the user run solaris
     */
    public static boolean isSolaris()
    {
        return (osName.indexOf("sunos") >= 0);
    }

    /**
     * @return Does the user run windows
     */
    public static boolean isWindows()
    {
        return (osName.indexOf("win") >= 0);
    }
}
