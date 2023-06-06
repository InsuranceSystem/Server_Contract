package Exception;

public class CustomConnectException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomConnectException(String message) {
		super(message);
	}

	public CustomConnectException(String message, Throwable cause) {
        super(message, cause);
    }
}
