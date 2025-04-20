package one;

import java.util.ArrayList;

public abstract class Tawjihi extends Student {

	private int seatingNum;
	private int year;
	private ArrayList<Subject> subjects = new ArrayList<>();;

	public abstract ArrayList<String> subjects();

	public Tawjihi(int seatingNum, int ID, String name, char gender, String school) {
		super(ID, name, gender, school);
		this.seatingNum = seatingNum;
		this.year = 2022;
	}

	public int getSeatingNum() {
		return seatingNum;
	}

	public void setSeatingNum(int seatingNum) {
		if (seatingNum >= 0)
			this.seatingNum = seatingNum;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if (seatingNum >= 0)
			this.seatingNum = seatingNum;
	}

	public abstract double calculateAverage();

	public void addSubject(Subject subject) {
		if (subject != null)
			subjects.add(subject);
	}

	public Subject getSubject(String title) {
		for (Subject subject : subjects) {
			if (subject.getTitle().equals(title))
				return subject;
		}
		return null;
	}

	public ArrayList<Subject> getSubjects() {
		ArrayList<Subject> s = subjects;
		return s;
	}

	public void setSubjectMark(String subjectName, int newMark) {
		for (int i = 0; i < subjects.size(); i++)
			if (subjects.get(i).getTitle().equals(subjectName)) {
				subjects.get(i).setMark(newMark);
				break;
			}
	}

	@Override
	public abstract String toString();

}