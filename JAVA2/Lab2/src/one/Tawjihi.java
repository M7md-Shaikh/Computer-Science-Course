package one;

import java.util.ArrayList;

class Tawjihi extends Student{

	private int seatingNum;
	private int year;
	private ArrayList<Subject> subjects;
	
	public Tawjihi( int seatingNum, int ID, String name, char gender, String school) {
		super(ID, name, gender, school);
		this.seatingNum=seatingNum;
		this.year=2022;
		this.subjects=new ArrayList<Subject>();
		
	}

	public int getSeatingNum() {
		return seatingNum;
	}

	public void setSeatingNum(int seatingNum) {
		if (seatingNum >=0)
			this.seatingNum = seatingNum;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if (seatingNum >=0)
			this.seatingNum = seatingNum;
	}

	public double calculateAverage() {
		// add a calculation logic
		return 0;
	}
	
	public void addSubject(Subject subject) {
		if (s != null)
			subjects.add(subject);

	}
	
	public Subject getSubject(String title) {
		for(Subject subject : subjects) {
			if (subject.getTitle().equals(title))
				return subject;
		}
		return null;
	}

	public void setSubjectMark(String subjectName , int newMark) {
		for (int i = 0; i < subjects.size(); i++)
			if (subjects.get(i).getTitle().equals(subjectName)) {
				subjects.get(i).setMark(newMark);
				break;
			}
	}
		
	public abstract double calculateAverage() {}

	@Override
	public abstract String toString() {}

	
}

class Scientific extends Tawjihi{

	public Scientific(int seatingNum, int ID, String name, char gender, String school) {
		super(seatingNum, ID, name, gender, school);
		
	}
	
	public double calculateAverage() {
		// add a calculation you need
		return 0;
	}
	
	@Override
	public final ArrayList<String> subjects () {
		ArrayList<String> s = new ArrayList<>(Arrays.asList("Arabic" , "English" ,
				"Physics" , "Math", "Biology" , "Chemistry" , "Religious Education",
				"Technology"));
		return s;
	}
	
	
	@Override
	public double calculateAverage() {
		double sum = 0;
		boolean E1 = true , E2 = true;
		ArrayList<Subject> subjects = super.getSubjects();
		for (int i = 0; i < subjects.size(); i++) {
			String s = subjects.get(i).getType();
			if (s.equals("Mandatory"))
				sum += subjects.get(i).getMark();
			else if (s.equals("Elective I") && E1) {
				sum += subjects.get(i).getMark();
				E1 = false;
			}else if (s.equals("Elective II") && E2) {
				sum += subjects.get(i).getMark();
				E2 = false;
			}
		}
		return sum / 7;
	}

	@Override
	public String toString() {
		return "Seating Number : " + super.getSeatingNumber() + "Student Name : " +
				super.getName()+ "Average : " + this.calculateAverage();
	}

	
}

class Literary extends Tawjihi {

	public Literary(int seatingNum, int ID, String name, char gender, String school) {
		super(seatingNum, ID, name, gender, school);
		
	}
	
	@Override
	public final ArrayList<String> subjects() {
		ArrayList<String> s = new ArrayList<>(Arrays.asList("Arabic" , "English" ,
				"Physics" , "History", "Geography" , "Religious Education" , "Scientific
				Culture", "Technology"));
				return s;
	}

	
	public double calculateAverage() {
		// add a calculation you need
		return 0;
	}
	
	public double calculateAverage() {
		double sum = 0;
		boolean E1 = true 
		boolean	E2 = true;
		ArrayList<Subject> subjects = super.getSubjects();
		for (int i = 0; i < subjects.size(); i++) {
			String s = subjects.get(i).getType();
			if (s.equals("Mandatory"))
				sum += subjects.get(i).getMark();
			else if (s.equals("Elective I") && E1) {
				sum += subjects.get(i).getMark();
				E1 = false;
			}else if (s.equals("Elective II") && E2) {
				sum += subjects.get(i).getMark();
				E2 = false;
			}
		}
		return sum / 7;
	}
	
	@Override
	public String toString() {
		return "Seating Number : " + super.getSeatingNumber() + "Student Name : " +
				super.getName()+ "Average : " + this.calculateAverage();
	}
	
	
}
