package info.coremodding.api.output;

import java.util.Date;
import java.util.logging.Level;

/**
 * Logging utils
 */
class LogHelper {

    /**
     * logging functionality
     *
     * @param msg The message
     * @param lvl The level of the log
     */
    public static void log(String msg, Level lvl) {
        String time = new Date().toString();
        System.out.println("[" + time + "]" + " [" + lvl + "] " + ": " + msg);
    }

    /**
     * logging functionality
     *
     * @param msg The message
     * @param bdl The Debug Mode Booleans Location
     * @param lvl The level of the log
     */
    public static void log(String msg, Level lvl, boolean bdl) {
        if (bdl) {
            String time = new Date().toString();
            System.out.println("[" + time + "]" + " [" + lvl + "] " + ":" + msg);
        }
    }
}
