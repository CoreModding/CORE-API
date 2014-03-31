package info.coremodding.api.net.exception;

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
    
    @Override
    public String getMessage()
    {
        return "Something went wrong during an FTP transfer!";
    }
}
