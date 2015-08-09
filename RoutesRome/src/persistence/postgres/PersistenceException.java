package persistence.postgres;

public class PersistenceException extends Exception {

	private static final long serialVersionUID = 1L;

	public PersistenceException(String message) {
		System.out.println(message);
	}

}
