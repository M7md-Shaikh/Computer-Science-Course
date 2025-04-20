package one;
import java.util.Scanner;
import java.util.ArrayList;

public class Act1 {

	public static Integer max(ArrayList<Integer> list) {
		
		if (list.size()==0 || list == null) {
			return null ;
		}
		
		int max = list.get(0);
		for (int num : list) {
			if(num > max)
				max=num;
		}
		return max;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Integer> list = new ArrayList<>();
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter a numbers , if you end put 0 please : ");
		
		int input = in.nextInt();;
		
		do {
			input = in.nextInt();
			if(input!=0)
				list.add(input);
		}while(input != 0 );
			
		Integer maxNum = max(list);
		  if (maxNum != null) {
	            System.out.println("The maximum number is: " + maxNum);
	      }
		  else {
			  System.out.println("The list is empty or null.");
		  }
	}
		
}


