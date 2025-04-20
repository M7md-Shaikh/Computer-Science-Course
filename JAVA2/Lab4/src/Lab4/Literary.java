package Lab4;

import java.util.ArrayList;
import java.util.Arrays;

class Literary extends Tawjihi {

	public Literary(int seatingNum, int ID, String name, char gender, String school) {
		super(seatingNum, ID, name, gender, school);
		
	}
	
	@Override
	public final ArrayList<String> subjects() {
		ArrayList<String> s = new ArrayList<>(Arrays.asList("Arabic" , "English" ,
				"Physics" , "History", "Geography" , "Religious Education" , "Scientific Culture", "Technology"));
				return s;
	}
	
	public double calculateAverage() {
		double sum = 0;
		boolean E1 = true ;
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
		return "Seating Number : " + super.getSeatingNum() + "Student Name : " +
				super.getName()+ "Average : " + this.calculateAverage();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
