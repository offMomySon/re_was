package request;

public class HttpStartLineException extends RuntimeException {
    public HttpStartLineException(String message) {
        super(message);
    }

    public HttpStartLineException(String message, Throwable cause) {
        super(message, cause);
    }
}
