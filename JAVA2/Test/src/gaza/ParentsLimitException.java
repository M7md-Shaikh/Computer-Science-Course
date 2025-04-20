package gaza;

// this is custom Exception until user can't add more than 2 parents
public class ParentsLimitException extends Exception {
	public ParentsLimitException(String message) {
        super(message);
    }
}
