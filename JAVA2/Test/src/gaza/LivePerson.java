package gaza;

// extends with Person class

public class LivePerson extends Person implements Cloneable{

	// constructors 
	
	public LivePerson() {}
	
	public LivePerson(String id, String name, int age, String gender, String address, String contactlnfo)
			throws IllegalArgumentException{
		super(id, name, age, gender, address, contactlnfo);
		
		if (age < 0) {
            throw new IllegalArgumentException("Invalid age! Age must be positave number");
		}
	}

	// a overriding clone for made a deep copy
	 @Override
	    public LivePerson clone() {
	        try {
	            LivePerson cloneLive = (LivePerson) super.clone();
	            return cloneLive;
	        } catch (CloneNotSupportedException e) {
	        	System.out.println("Clone Not Supported Exception "+e.getMessage());
	        	return null;
	        }
	    }
	
	@Override
	public String toString() {
		return "LivePerson [ ID =" + getID() + ", Name =" + getName() + ", Age =" + getAge()
				+ ", Gender =" + getGender() + ", Addres =" + getAddress() + ", Contactlnfo ="
				+ getContactInfo() + "]";
	}
}