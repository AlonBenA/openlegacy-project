package openlegacy.logic;

public class ItemAlreadyExistsException extends RuntimeException {

	/**
	 * To create a special exception when find 2 of the same item
	 */
	private static final long serialVersionUID = -3666140049323386893L;

	
	
	public ItemAlreadyExistsException() {
	}

	public ItemAlreadyExistsException(String message) {
		super(message);
	}

	public ItemAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public ItemAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

}
