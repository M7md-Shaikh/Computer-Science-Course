package gaza;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {

	// until user can input any thing about family and total about it
	public static void displayMenu() {
		System.out.println("------------------------------");
		System.out.println("1. Add Family");
		System.out.println("2. Update Family");
		System.out.println("3. Delete Family");
		System.out.println("4. Search By Family Name");
		System.out.println("5. Search By Person ID");
		System.out.println("6. Calculate Total Martyr");
		System.out.println("7. Calculate Total Orphans");
		System.out.println("8. Calculate Total Live Person");
		System.out.println("9. Calculate Family Statistics");
		System.out.println("10. Calculate Global Statistics");
		System.out.println("11. Sort the Families by martyr");
		System.out.println("12. Sort the Families by orphans");
		System.out.println("13. Deep copy ");
		System.out.println("14. if you need to print familis to file");
		System.out.println("0. Exit");
		System.out.println("Please Enter Your Choice : ");
		
	}

	
	
	// main method have a menu display for user
	public static void main(String[] args) {
	 try {	
		Scanner in = new Scanner(System.in);
		Manager manager = new Manager();
		Family family=new Family();
		String file=null;
		int ch = 0;
		do {
		   // Here I want the user to choose if he need use a console or file 
		   System.out.println("Hello! , You should choose what you need to use : ");
		   System.out.println("1.Console");
		   System.out.println("2.File");
		   int i = in.nextInt();
		
		   // first switch for if user choose what he need
		
	     	switch(i) {
	 	    case 1 :
	 	    	break;
		    case 2 : 
		    	System.out.println("Enter (1) if you need to Write in the File \nEnter (2) if you need to read from the File (and edit it if u need) : ");
		    	ch = in.nextInt();
		    	switch(ch) {
		    		case 1: 
		    			System.out.println("Enter file name (with .txt please) :");
		    			in.nextLine();
		    			file = in.nextLine();
		    			break;
				
		    		case 2 : 
		    			System.out.println("Enter file name (with .txt please) :");
		    			in.nextLine();
		    			String familyFile = in.nextLine();
		    			manager.editFamilyFromFile(familyFile);
		    			break;
		    	}
		    	break;
		   default :
			   System.out.println("Please Enter a Valid Option");	
	     	}
		break;
		} while (ch != 1 && ch !=2 );
		
		
		// Secound switch for user enter what he need 
		int choice;
		do {
		   displayMenu();
		   choice = in.nextInt();
		   switch(choice) {
				case 1:
					System.out.println("Enter a Family name :");
					in.nextLine();
					String familyName=in.nextLine();
					Family familyN= new Family(familyName);
					manager.addFamily(familyN);
					if (ch ==1 ) {
						  manager.WriteAllFamilies(file);
					  }
					break;
				
				case 2:
					System.out.println("Enter a Family name : ");
					in.nextLine();
					String familyname = in.nextLine();
					manager.updateFamily(familyname, family);
					if (ch ==1 ) {
						  manager.WriteAllFamilies(file);
					  }
					break;
					
				case 3:
					System.out.println("Enter Family name you need to Delete : ");
					in.nextLine();
					String familyToDelete = in.nextLine();
					manager.deleteFamily(familyToDelete);
					if (ch ==1 ) {
						  manager.WriteAllFamilies(file);
					  }
					break;
						
				case 4:
					System.out.println("Enter Family name to Search :");
					in.nextLine();
					manager.searchByName(in.nextLine());
					
					break;
					
				case 5:
					System.out.println("Enter Person ID to Search :");
					in.nextLine();
					String searchByPerID=in.nextLine();
					manager.searchPersonByID(searchByPerID);
					break;
					
				case 6:
					manager.calculateTotalMartyrs();
					break;
						
				case 7:
					
					manager.calculateTotalOrphans();
					break;
					
				case 8 :		
					
					manager.calculateTotalLivePersons();
					break;

				case 9 :	
					System.out.println("Enter Family Name For Statistics : ");
					in.nextLine();
					String familyForSta = in.nextLine();
					manager.calculateFamilyStatistics(familyForSta);
					break;
					
				case 10: 
					manager.calculateGlobalStatistics();
					break;
					
				case 11 : 
					family.sortByMartyrs(manager.families);
					break;
				
				case 12 : 
					family.sortByOrphans(manager.families);
					break;
					
				case 13 : 
					System.out.println(family.clone());
					Martyr m = new Martyr();
					System.out.println(m.clone());
					LivePerson l = new LivePerson();
					System.out.println(l.clone());    
					break;
					
				case 14 : 
					
					System.out.println("Enter file name (with .txt please) :");
					in.nextLine();
					String fileW = in.nextLine();
					manager.WriteAllFamilies(fileW);
					break;
					
				case 0:
					System.out.println("Exiting the Program , Thank you and Goodbye :)");
					System.exit(0);
					
				default :
					System.out.println("Please Enter a Valid Option");	
			 }
		   
		}while(choice != 0);
	
		}catch(InputMismatchException e) {
			 System.out.println("You Should Enter Integer "+e.getMessage());
		 }
	}
}