package Lab4;

import java.util.ArrayList;

import javax.security.auth.Subject;

public class Student extends Human{

	private int ID;
	private String name;
	private char gender ;
	private String school;
	
	public Student (int ID , String name , char gender , String school) {
		this.ID = ID;
		this.name = name;
		this.gender=gender;
		this.school=school;
	}
	
	// getter and setter :
	
	public int getID() {
		return ID ; 
	}
	public void setID(int ID) {
		if (ID > 0 ) 
			this.ID=ID;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		if (gender == 'M' || gender == 'F')
			this.gender = gender;
	}
	
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school=school;
	}

	@Override
	public String toString() {
		return "Student [ID=" + ID + ", name=" + name + ", gender=" + gender + ", school=" + school + "]";
	}
}
