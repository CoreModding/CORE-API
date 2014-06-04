package info.coremodding.api.locations.exceptions;

/**
 * @author James
 *         The direction given is invalid
 */
public class InvalidDirectionException extends Exception
{
    
    /**
     * The serial ID for this exception
     */
    private static final long serialVersionUID = 444067087836649228L;
    
    @Override
    public String getMessage()
    {
        return "The provided direction is invalid!";
    }
}
