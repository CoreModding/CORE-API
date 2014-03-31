package info.coremodding.api.net.exception;

/**
 * @author James
 *         The reply of an FTP request was bad in a fatal way </3
 */
public class BadFTPReplyException extends FTPError
{
    
    /**
     * The serial ID for this exception
     */
    private static final long serialVersionUID = 1847347999547034500L;
    
    @Override
    public String getMessage()
    {
        return "There has been an error: Bad FTP reply code";
    }
}
