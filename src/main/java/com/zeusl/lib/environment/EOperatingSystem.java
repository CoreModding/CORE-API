package com.zeusl.lib.environment;

/**
 * @author Zeus
 *         The operating systems
 */
public enum EOperatingSystem
{
    /**
     * The linux operating system
     * Somewhat common, kicks ass
     */
    Linux("Linux"),

    /**
     * The mac operating system
     * It's pretty uncommon, because it sucks balls
     */
    Mac("MacIntosh"),

    /**
     * What the fuck are you running man?
     */
    Other("Other"),

    /**
     * What the fuck is solaris anyway?
     */
    Solaris("Solaris"),

    /**
     * The windows operating system
     * The most common and best known operating system
     * It's pretty good
     */
    Windows("Windows");

    private String name;

    private EOperatingSystem(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
