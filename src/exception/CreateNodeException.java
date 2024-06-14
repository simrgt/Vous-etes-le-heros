package exception;

public class CreateNodeException extends RuntimeException {
    public CreateNodeException(String error, Throwable cause) {
        super(error, cause);
    }
}
