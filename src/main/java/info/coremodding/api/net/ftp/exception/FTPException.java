package info.coremodding.api.net.ftp.exception;

/**
 * @author James
 *         A general FTP error
 */
public class FTPException extends Exception
{
    
    /**
     * The serial ID for this exception
     */
    private static final long serialVersionUID = 602472895511263908L;
    
    private String            message          = "Something went wrong during an FTP transfer!";
    
    /**
     * @param message
     *            The error message for display
     */
    public FTPException(String message)
    {
        this.message = message;
    }
    
    /**
     * A constructor. Uses the default message.
     */
    public FTPException()
    {
    }
    
    @Override
    public String getMessage()
    {
        return this.message;
    }
}
