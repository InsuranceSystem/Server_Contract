package Exception;

public class DaoException extends Exception {
	private static final long serialVersionUID = 1L;
	private String daoMethodName;

    public DaoException(String message, String daoMethodName) {
        super(message);
        this.daoMethodName = daoMethodName;
    }

    public String getDaoMethodName() {
        return daoMethodName;
    }
}