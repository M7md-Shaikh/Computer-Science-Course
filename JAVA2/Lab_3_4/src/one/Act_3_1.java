package one;

import java.util.ArrayList;

public class Act_3_1 {

	public static void shuffle(ArrayList<Number> list) {

		for (int i = 0; i < list.size() - 1; i++) {
			int j = (int) (Math.random() * (i + 1));
			int num = (int) list.get(i);
			list.set(i, list.get(j));
			list.set(j, num);
		}
	}

	public static void main(String[] args) {

		ArrayList<Number> list = new ArrayList<>();
		list.add(10);
		list.add(20);
		list.add(30);
		list.add(40);
		list.add(50);

		shuffle(list);

		System.out.println("Shuffled list: " + list);
	}
}