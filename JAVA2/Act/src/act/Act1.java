package act;
import java.util.ArrayList;
import java.util.Scanner;

public class Act1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		ArrayList<Integer> list = new ArrayList<>();
		int num ;
		
		do {
			System.out.println("Please enter a numbers ( a negative num to stop) : ");
			num = in.nextInt();
			if(num > 0) {
				list.add(num);
			}
		}while (num >= 0);
		
		ArrayList<Integer> newList = new ArrayList<>();
		for (int number : list) {
			if(number % 2 != 0) 
				newList.add(number);
		}
		for (int number : list) {
			if (number % 2 ==0)
				newList.add(number);
		}
		
		System.out.println("Rearranged List is :"+newList);
		
	}

}
