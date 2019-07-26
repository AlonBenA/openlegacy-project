package openlegacy.logic;

public class ItemNotFoundException extends Exception {

	/**
	 * To create a special exception when can't find the item
	 */
	private static final long serialVersionUID = 7030427955263259890L;
	
	public ItemNotFoundException() {
		super();
	}

	public ItemNotFoundException(String message) {
		super(message);
	}

	public ItemNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItemNotFoundException(Throwable cause) {
		super(cause);
	}


}
