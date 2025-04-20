package Lab4;


public abstract class Human implements HumanBeing {

	private String name;
	private char gender;

	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (name != null)
			this.name = name;
	}
	
	public char getGender() {
		return gender;
	}
	
	
	public void setGender(char gender) {
		if (gender == MALE || gender == FEMALE)
			this.gender = gender;
	}
}
