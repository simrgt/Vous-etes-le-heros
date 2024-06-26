package exception;

/**
 * Represents an exception when creating a node.
 */
public class CreateNodeException extends RuntimeException {
    /**
     * @param error message of the error
     * @param cause cause of the error
     */
    public CreateNodeException(String error, Throwable cause) {
        super(error, cause);
    }
}
