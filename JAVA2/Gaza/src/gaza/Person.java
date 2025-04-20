package gaza;

public abstract class Person implements Cloneable{
	
	private String ID;
	private String Name;
	private int Age;
	private String Gender;
	private String Address;
	private String ContactInfo;
	protected boolean clone;

	public Person () {}
	
	// A constructors with a parameter for inheritance class 
	
	public Person(String id, String name, int age, String gender, String address, String contactInfo) {
		this.ID = id;
		this.Name = name;
		this.Age = age;
		this.Gender = gender;
		this.Address = address;
		this.ContactInfo = contactInfo;
	}
	
	// Getter and Setter methods : 

	@Override
    public Person clone() throws CloneNotSupportedException {
        try {
            return (Person) super.clone();
        } catch (CloneNotSupportedException e) {
            // handle the exception
            return null;
        }
    }
	
	public String getID() {
		return ID;
	}

	public void setID(String id) {
		this.ID = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		this.Age = age;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		this.Gender = gender;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		this.Address = address;
	}

	public String getContactInfo() {
		return ContactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.ContactInfo = contactInfo;
	}

	
	@Override
	public String toString() {
		return "Person [ID=" + ID + ", Name=" + Name + ", Age=" + Age + ", Gender=" + Gender + ", Addres=" + Address
				+ ", Contactlnfo=" + ContactInfo + "]";
	}
	
}
