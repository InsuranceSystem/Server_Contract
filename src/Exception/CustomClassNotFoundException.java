package Exception;

public class CustomClassNotFoundException extends ClassNotFoundException {
	private static final long serialVersionUID = 1L;

	public CustomClassNotFoundException(String message) {
		super(message);
	}
}