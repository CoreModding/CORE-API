package com.zeusl.lib.environment;

/**
 * @author Zeus
 *         The operating systems
 */
public enum EOperatingSystem
{
    
    /**
     * The linux operating system
     */
    Linux("Linux"),

    /**
     * The mac operating system
     */
    Mac("MacIntosh"),

    /**
     * An unknown operating system
     */
    Other("Other"),

    /**
     * The oracle Solaris operating system
     */
    Solaris("Solaris"),

    /**
     * The windows operating system
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
