package info.coremodding.api.output;

/**
 * Logging utils
 */
public class LogHelper {

	/**
	 * Basic logging functionality
	 * 
	 * @param msg The message
	 */
	public static void log(String msg){
		System.out.println(msg);
	}
	
	/**
	 * @param msg The message
	 * @param dbl The Debug Mode Booleans Location
	 */
	public static void log(String msg, boolean bdl){
		if (bdl == true){
			System.out.println(msg);
		}
	}
}
