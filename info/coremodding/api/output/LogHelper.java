package info.coremodding.api.output;

import java.util.Date;
import java.util.logging.Level;

/**
 * Logging utils
 */
public class LogHelper {

	/**
	 * logging functionality
	 * 
	 * @param msg The message
	 * @param lvl The level of the log
	 */
	public static void log(String msg, Level lvl){
		Date date = new Date();
		String time = date.toString();
		System.out.println("[" + time + "]" + " [" + lvl + "] "+" :" + msg);
	}
	
	/**
	 * logging functionality
	 * 
	 * @param msg The message
	 * @param dbl The Debug Mode Booleans Location
	 * @param lvl The level of the log
	 */
	public static void log(String msg, Level lvl ,boolean bdl){
		if (bdl == true){
			Date date = new Date();
			String time = date.toString();
			System.out.println("[" + time + "]" + " [" + lvl + "] "+" :" + msg);
		}
	}
}
