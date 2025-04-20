package one;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<Tawjihi> tawjihi = new ArrayList<>();
		tawjihi.add(new Scientific(1, 1, "Mahmoud", 'M', "Birziet"));
		tawjihi.add(new Literary(2, 2, "Mariam", 'F', "Ramallah Sec"));
		tawjihi.add(new Scientific(3, 3, "M7md", 'M', "Kharbatha"));
		for (int i = 0; i < tawjihi.size(); i++) {
			Tawjihi student = tawjihi.get(i);
			for (int j = 0; j < student.subjects().size(); j++) {
				System.out.println("Please enter mark of " + student.subjects().get(j) + " subject for student#"
						+ student.getID() + " :");
				int mark = in.nextInt();
				String type;
				if (j == 4 || j == 5)
					type = "Elective I";
				else if (j == 6 || j == 7)
					type = "Elective II";
				else
					type = "Mandatory";
				student.addSubject(new Subject(student.subjects().get(j), mark, type));
			}
			System.out.println("The average of student #" + student.getID() + " is :" + student.calculateAverage());
		}
	}
}
