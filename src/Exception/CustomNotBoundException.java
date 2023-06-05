package Exception;

import java.rmi.NotBoundException;

public class CustomNotBoundException extends NotBoundException {
	private static final long serialVersionUID = 1L;
	public CustomNotBoundException(String message) {
		super(message);
	}
}
