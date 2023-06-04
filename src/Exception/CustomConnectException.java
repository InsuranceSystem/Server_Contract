package Exception;

import java.net.ConnectException;

public class CustomConnectException extends ConnectException {

	private static final long serialVersionUID = 1L;

	public CustomConnectException(String message) {
		super(message);
	}
}
