package gaza;

// extends with Person class

public class Martyr extends Person implements Cloneable{
	
	
	private String DateOfMartyrdom;
	private String CauseOfDeath;
	private String PlaceOfDeath;

	public Martyr(String id, String name, int age, String gender, String address,
			String contactlnfo , String DateOfMartyrdom,String CauseOfDeath , String PlaceOfDeath) 
					throws IllegalArgumentException{
		super(id, name, age, gender, address, contactlnfo);  // super from Person class
		 
		if (age < 0) {
	            throw new IllegalArgumentException("Invalid age! Age must be positave number ");
		}
		
		this.DateOfMartyrdom=DateOfMartyrdom;
		this.CauseOfDeath=CauseOfDeath;
		this.PlaceOfDeath=PlaceOfDeath;
		
		System.out.println("Added martyt Successfull!");
	}

	// Getter and Setter Methods : 
	
	public Martyr() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getDateOfMartyrdom() {
		return DateOfMartyrdom;
	}

	public void setDateOfMartyrdom(String dateOfMartyrdom) {
		this.DateOfMartyrdom = dateOfMartyrdom;
	}

	public String getCauseOfDeath() {
		return CauseOfDeath;
	}

	public void setCauseOfDeath(String causeOfDeath) {
		this.CauseOfDeath = causeOfDeath;
	}

	public String getPlaceOfDeath() {
		return PlaceOfDeath;
	}

	public void setPlaceOfDeath(String placeOfDeath) {
		this.PlaceOfDeath = placeOfDeath;
	}
	
	// overriding clone for make deep copy
	@Override
	public Martyr clone() {
	    try {
	        Martyr cloneMartyr = (Martyr) super.clone(); // call the clone method of the Person class
	        // copy or clone the fields of the Martyr class here
	        return cloneMartyr;
	    } catch (CloneNotSupportedException e) {
	        // handle the exception
	        return null;
	    }
	}
	
	@Override
	public String toString() {
		return "Martyr [ID = " + getID() + ", Name =" + getName()
				+ ", Age=" + getAge() + ", Gender =" +getGender() + ", Address =" + getAddress()
				+ ", Contactlnfo =" + getContactInfo() + ", Date Of Martyrdom =" + getDateOfMartyrdom() 
				+ ", Cause Of Death()=" + getCauseOfDeath()+ ", Place Of Death()=" + getPlaceOfDeath() + "]";
	}
	
	
	
}
