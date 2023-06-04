package Exception;

import java.sql.SQLException;

public class CustomSQLException extends SQLException {

	private static final long serialVersionUID = 1L;

	public CustomSQLException(String message) {
		super(message);
	}
}
