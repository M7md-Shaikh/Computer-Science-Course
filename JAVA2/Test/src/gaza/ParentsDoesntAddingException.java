package gaza;

// this is custom Exception to ensure that it is forbidden for children to be added before parents
public class ParentsDoesntAddingException extends Exception {
	 public ParentsDoesntAddingException(String message) {
	        super(message);
	    }
}
