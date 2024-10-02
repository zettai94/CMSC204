

/*
 * 1.	Password doesn’t contain a special character (class NoSpecialCharacterException)
Message – The password must contain at least one special character

 */
public class NoSpecialCharacterException extends Exception {
	
	public NoSpecialCharacterException() {
		super("The password must contain at least one special character");
	}

}
