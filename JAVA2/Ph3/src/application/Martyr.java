package application;

public class Martyr {
	
	
	private String MartyrName;
	private String DateOfMartyrdom;
	

	public Martyr(String MartyrName , String DateOfMartyrdom) {
		
		this.DateOfMartyrdom=DateOfMartyrdom;
		this.MartyrName=MartyrName;
	}
	
	public Martyr() {}
	
	
	//Getter and Setter Methods : 
	
	public String getMartyrName() {
		return MartyrName;
	}
	
	public void setmartyrName(String martyrName) {
		this.MartyrName=martyrName;
	}
	
	public String getDateOfMartyrdom() {
		return DateOfMartyrdom;
	}

	public void setDateOfMartyrdom(String dateOfMartyrdom) {
		this.DateOfMartyrdom = dateOfMartyrdom;
	}

	@Override
	public String toString() {
		return "Martyr [MartyrName=" + MartyrName + ", DateOfMartyrdom=" + DateOfMartyrdom + "]";
	}
	
}