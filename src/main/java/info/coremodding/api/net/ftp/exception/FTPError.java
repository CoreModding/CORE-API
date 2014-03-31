package info.coremodding.api.net.ftp.exception;

/**
 * @author James
 *         A general FTP error
 */
public class FTPError extends Exception
{
    
    /**
     * The serial ID for this exception
     */
    private static final long serialVersionUID = 602472895511263908L;
    
    private String            message          = "Something went wrong during an FTP transfer!";
    
    /**
     * @param message
     *            The error message
     */
    public FTPError(String message)
    {
        this.message = message;
    }
    
    /**
     * Default message
     */
    public FTPError()
    {
    }
    
    @Override
    public String getMessage()
    {
        return this.message;
    }
}
