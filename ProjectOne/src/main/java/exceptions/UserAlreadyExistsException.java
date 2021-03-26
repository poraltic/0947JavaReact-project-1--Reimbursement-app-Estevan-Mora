package exceptions;

public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 949302012582863103L;
	
	public UserAlreadyExistsException(String message) {
		super(message);
	}

}
