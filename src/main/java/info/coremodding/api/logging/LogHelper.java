package info.coremodding.api.logging;

import java.util.Date;
import java.util.logging.Level;

/**
 * Logging utils. It's advised to import static so that you dont have to always
 * call LogHelper.*
 */
public class LogHelper
{

    /**
     * Logs a message
     * 
     * @param msg
     *            The message to log
     * @param lvl
     *            The severity level
     */
    public static void log(String msg, Level lvl)
    {
        String time = new Date().toString();
        System.out.println("[" + time + "]" + " [" + lvl.getName() + "] "
                + ": " + msg);
    }

    /**
     * logging functionality
     * 
     * @param msg
     *            The message to log
     * @param bdl
     *            Used for having 1 log instance but only having things activate
     *            if debug is on. Just pass if debug is on here and it will auto
     *            do/not do the log. No huge if stacks. Yay!
     * @param lvl
     *            The serverity level
     */
    public static void log(String msg, Level lvl, boolean bdl)
    {
        if (bdl)
        {
            String time = new Date().toString();
            System.out
                    .println("[" + time + "]" + " [" + lvl + "] " + ":" + msg);
        }
    }
}
