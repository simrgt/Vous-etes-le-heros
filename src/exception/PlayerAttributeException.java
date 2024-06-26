package exception;

/**
 * exception.AttributeException class
 * Represent the exception that is thrown when an attribute is not found in the Character enum.
 */
public class PlayerAttributeException extends RuntimeException {
    /**
     * @param attribute attribute that was not found
     */
    public PlayerAttributeException(String attribute) {
        super("Attribute " + attribute + " not found");
    }

    /**
     * Default constructor
     */
    public PlayerAttributeException() {
        super("Attribute not found");
    }
}
