package info.coremodding.api.exceptions;

/**
 * @author James
 *         An invalid value
 */
public class InvalidValueException extends Exception
{
    
    /**
     * The serial ID
     */
    private static final long serialVersionUID = 3903022291606674658L;
    private String            message          = "There was an invalid value!";
    
    /**
     * The constructor
     */
    public InvalidValueException()
    {
        return;
    }
    
    /**
     * @param message
     *            The message to use
     */
    public InvalidValueException(String message)
    {
        this.message = message;
    }
    
    @Override
    public String getMessage()
    {
        return this.message;
    }
}
