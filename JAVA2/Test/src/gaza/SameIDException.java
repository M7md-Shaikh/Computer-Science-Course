package gaza;

// to ensure that it is forbidden to add two member in the same family that contain the same ID
public class SameIDException extends Exception {
	public SameIDException (String message) {
		super(message);
	}
}
