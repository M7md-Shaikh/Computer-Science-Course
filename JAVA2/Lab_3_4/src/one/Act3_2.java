package one;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Act3_2 {

	public static void main(String[] args) {

		Calendar calendar = new GregorianCalendar(2022, 7, 1);
		int month = calendar.MONTH;
		System.out.println("                   " + monthName(month) + "   2022");
		System.out.println("--------------------------------------------------");
		System.out.println("Sun\tMon\tTue\tWed\tThu\tFri\tSat");
		// System.out.println(calendar.get(Calendar.DAY_OF_WEEK)+ "\t");
		for (int i = 1; i < calendar.get(Calendar.DAY_OF_WEEK); i++) {
			System.out.print("\t");
		}
		for (int m = 1; m <= 31; m++) {
			System.out.printf(m + "\t");
			if ((m + calendar.get(Calendar.DAY_OF_WEEK) - 1) % 7 == 0) {
				System.out.println();
			}

		}

	}

	public static String monthName(int month) {
		String[] monthsName = { "January", "February", "March", "April", "may", "Jun", "July", "August", "Septemper",
				"October", "November", "December" };
		return monthsName[month];
	}
}