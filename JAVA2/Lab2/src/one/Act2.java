package one;
import java.util.ArrayList;
import java.util.Scanner;
public class Act2 {

	public static void removeDuplicate(ArrayList<Integer> list) {
		
		ArrayList<Integer> newList = new ArrayList<>();
		for(int i=0 ; i<10 ; i++) {
			if(!newList.contains(list.get(i))) {
				newList.add(list.get(i));
			}
		}
		
		list.clear();
		for (int i =0 ; i<newList.size(); i++) {
			list.add(newList.get(i));
		}
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Integer> list = new ArrayList<>();
		Scanner in = new Scanner(System.in);
		
		System.out.println("Please enter 10 numbers : ");
		
		for(int i =0 ; i<10 ; i++) {
			int num = in.nextInt();
			list.add(num);
		}
		
		removeDuplicate(list);
		System.out.println("Distinct Numbers you entered : ");
		for(int x : list) {
			System.out.print(x+" ");
		}
	}
}
