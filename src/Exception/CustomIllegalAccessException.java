package Exception;

public class CustomIllegalAccessException extends IllegalAccessException {

	private static final long serialVersionUID = 1L;

	public CustomIllegalAccessException(String message) {
		super(message);
	}
}