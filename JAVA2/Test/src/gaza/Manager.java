package gaza;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Manager {
	
	static ArrayList<Family> families;
	Driver drive = new Driver();
	
	// Constructor methodsِ
	public Manager() {
		this.families = new ArrayList<>();
	}
	
	// this method to add a Family to Array List (Family registry)
	public boolean addFamily(Family family)  {
		try{
		Scanner in = new Scanner(System.in);
	    if (!families.contains(family)) {
	    	
	    	// Add the Family to the Family list
	        families.add(family);
	        System.out.println("Family Number " + families.size() + " Added Successfully!");
	        int choice;
			do {
	            System.out.println("1. Add Member (Live Person) \n2. Add Martyr");
	            choice = in.nextInt();

	            // here i need a user enter what he need to add to a family
	            switch (choice) {
	            
                case 1:
                    forMember();
                    break;
                    
                case 2:
                    forMartyr();
                    break;
                    
                default:
                    System.out.println("Please enter either (1) or (2)");
            }

        } while (choice != 1 && choice !=2 );

        return true;
	    
	    } else {
	        System.out.println("This Family is Found before that");
	        return false;
	    }
		}catch(InputMismatchException e) {
	    	System.out.println("Please enter a Integer ");
	    }
		return false;
	}
	
	
	// This method to update a Family names 
	public boolean updateFamily(String familyName , Family updateFamily)  {
		Scanner in = new Scanner(System.in);
		for (Family family : families) {
			if (family.getFamilyName().equals(familyName)) {
				int choice;
				do {
				// we defined updateFamily a object , we need add a method get name to it until update
				System.out.println("choose what you need to do :");
				System.out.println("1. Add Member");
				System.out.println("2. Remove Member");
				System.out.println("3. Add Parent");
				System.out.println("4. Remove Parent");
				System.out.println("5. Update Family Name");
				System.out.println("6. Add Martyr");

				choice=in.nextInt();
				switch(choice) {
					case 1:
						
						forMember(); // for adding member
						break;
					
					case 2 :
						updateFamily.removeMember(forMember());  // for remove Member
						break;
						
					case 3:
						updateFamily.addParent(forMember()); // for add parent
						break;
						
					case 4:
						updateFamily.removeParent(forMember());  // remove Parent
						break;
						
					case 5:
	                    System.out.println("Enter new family name:");
	                    in.nextLine();
	                    String newFamilyName = in.nextLine();
	                    family.setFamilyName(newFamilyName); // for Update the Family Name
	                    break;	
	                    
					case 6 : 
						forMartyr();  // for adding Martyr
						break;
				}
				System.out.println("Family update is Successfully!");
				
				}while (choice != 1 && choice != 2 &&choice != 3 &&choice != 4&&choice !=5 && choice !=6);
			return true;
			}
		}
		System.out.println("Not Found a Family!");
		return false; // not successful
	}
	
	
	// This method for delete a family name from array (Family registry)
	public boolean deleteFamily(String familyName) {
		for(Family family: families) {
			if(family.getFamilyName().equals(familyName)) {
				families.remove(family); // successful
				System.out.println("Delete Family is Successful!");
				return true;
			}else {
				
			}
		}
		return false; 
	}
	
	
	// This method for search by a family name in a array(family registry) السجل العائلي
	public Family searchByName(String familyName) {
		for(Family family : families) {
			if(family.getFamilyName().equals(familyName)) {
				System.out.println("Found Family : "+familyName);
				System.out.println(family.getMembers());
				return family; // done
			}
		}
		
		System.out.println("Family Not Found!");
		return null; // family name not found
	}
	
	
	// This method to search for person by id 
	public Person searchPersonByID(String personID) {
	    for (Family family : families) {
	        for (Person person : family.getMembers()) {
	            if (person.getID().equals(personID)) {
	                System.out.println("Found Person: " + person);
	                return person;
	            }
	        }
	    }

	    System.out.println("Person with ID " + personID + " not found.");
	    return null;
	}
	
	
	// This method to calculate a martyr member in a family // لحساب عدد الشهداء
	// This method to calculate the total number of martyr members in all families
	public static int calculateTotalMartyrs() {
	    int totalMartyrs = 0;

	    for (Family family : families) {
	        for (Person person : family.getMembers()) {
	            if (person instanceof Martyr) {
	                totalMartyrs++; // increase martyr member count
	            }
	        }
	    }

	    System.out.println("Total Martyrs: " + totalMartyrs);
	    return totalMartyrs;//totalLivePersons
	}

	
	
	// This method to calculate a Orphans people or member in the Family // لحساب عدد اليتامى	
	public static int calculateTotalOrphans() {
		int totalOrphans = 0;
    	boolean hasMartyrMom = false;
    	boolean hasMartyrDad = false;
	    for (Family family : families) {
	    	for (Person member : family.getMembers()) {
	    		if (member instanceof Martyr) {
	    			//to show if the martyr is a parent
	    			if (family.getParent().contains(member)) {
	    				if (member.getGender().equalsIgnoreCase("female")) {
                        hasMartyrMom = true;
	    				} else if (member.getGender().equalsIgnoreCase("male")) {
                        hasMartyrDad = true;
	    				}
	    			}
	    		} 
                // to check if the live person is an orphan
                if (!family.getParent().contains(member)) {
                    totalOrphans++;
                }
            }
        }
	
        // If both mom and dad are martyrs, increase totalOrphans
        if (hasMartyrMom && hasMartyrDad) {
            totalOrphans++;
        }
        

	    System.out.println("Total Orphans is: " + totalOrphans);
	    return totalOrphans;
	}

	
	
	//this method to calculate live person in a family 
	public int calculateTotalLivePersons() {
		int totalLivePersons = 0;
	    for (Family family : families) {
	        for (Person person : family.getMembers()) {
	            if (!(person instanceof Martyr)) {
	                totalLivePersons++;
	            }
	        }
	    }

	    System.out.println("Total Live Persons: " + totalLivePersons);
	    return totalLivePersons;
	}



	/* we need in this method to calculate every members in the same family, if he live or martyr or orphans. 
	 this meaning we need to use the last 3 methods in this one . and but the result in arrays*/
	
	public ArrayList<Integer> calculateFamilyStatistics(String familyName) {
	    for (Family family : families) {
	        if (family.getFamilyName().equals(familyName)) {
	            int totalMartyrs = 0;
	            int totalOrphans = 0;
	            int totalLivePerson = 0;
	            boolean hasMartyrMom = false;
	            boolean hasMartyrDad = false;

	            for (Person member : family.getMembers()) {
	                if (member instanceof Martyr) {
	                    totalMartyrs++;

	                    // to check if the martyr is a parent
	                    if (family.getParent().contains(member)) {
	                        if (member.getGender().equalsIgnoreCase("female")) {
	                            hasMartyrMom = true;
	                        } else if (member.getGender().equalsIgnoreCase("male")) {
	                            hasMartyrDad = true;
	                        }
	                    }
	                } else {
	                    totalLivePerson++;

	                    // to check if the live person is an orphan
	                    if (!family.getParent().contains(member)) {
	                        totalOrphans++;
	                    }
	                }
	            }

	            // If both mom and dad are martyrs, increase totalOrphans
	            if (hasMartyrMom && hasMartyrDad) {
	                totalOrphans++;
	            }

	            ArrayList<Integer> familyStatistics = new ArrayList<>();
	            familyStatistics.add(totalMartyrs);
	            familyStatistics.add(totalOrphans);
	            familyStatistics.add(totalLivePerson);
	            System.out.println("Family Statistics for " + familyName + ": " + familyStatistics);
	            return familyStatistics;
	        }
	    }

	    System.out.println("Family Not Found!");
	    return null;
	}


	public ArrayList<Integer> calculateGlobalStatistics() {
	    int totalMartyrs = 0;
	    int totalOrphans = 0;
	    int totalLivePersons = 0;
	    boolean hasMartyrMom = false;
	    boolean hasMartyrDad = false;

	    for (Family family : families) {
	        for (Person member : family.getMembers()) {
	            if (member instanceof Martyr) {
	                totalMartyrs++;

	                // Check if the martyr is a parent
	                if (family.getParent().contains(member)) {
	                    if (member.getGender().equalsIgnoreCase("female")) {
	                        hasMartyrMom = true;
	                    } else if (member.getGender().equalsIgnoreCase("male")) {
	                        hasMartyrDad = true;
	                    }
	                }
	            } else {
	                totalLivePersons++;
	            }
	        }
	    }

	    // If both mom and dad are martyrs, increase totalOrphans
	    if (hasMartyrMom && hasMartyrDad) {
	        totalOrphans++;
	    }

	    ArrayList<Integer> globalStatistics = new ArrayList<>();
	    globalStatistics.add(totalMartyrs);
	    globalStatistics.add(totalOrphans);
	    globalStatistics.add(totalLivePersons);

	    System.out.println("Global Statistics: " + globalStatistics);
	    return globalStatistics;
	}


	@Override
	public String toString() {
		String result = "Manager : {\n" ;
		for(Family family : families) {
			result += "Family: " +family.getFamilyName() + "\n" ;
			result += "Members: "+family.getMembers() + "\n";
			result += "Parents: "+family.getParent() + "\n" + "}";
		}
		return result;
	}
	
	// a method from me to add martyr
	public void forMartyr()  {
		
		try {
		  Scanner in = new Scanner(System.in);
		  System.out.println("Enter the Name of Family: ");
	   	  String familyName = in.nextLine();
		  for (Family family : families) {
			if (family.getFamilyName().equals(familyName)) {
			  for (int i=1 ; i<= 100 ; i++) {
				System.out.println("Enter ID for Martyr #"+i+" : ");
				String ID = in.nextLine();
				System.out.println("Enter Martyr #"+i+" Name : ");
				String name = in.nextLine();
				System.out.println("Enter Martyr #"+i+" Age : ");
				int age = in.nextInt();
				System.out.println("Enter Martyr #"+i+" Gender : ");
				in.nextLine();
				String gender = in.nextLine();
				System.out.println("Enter Martyr #"+i+" Addres : ");
				String addres = in.nextLine();
				System.out.println("Enter Martyr #"+i+" Contactlnfo");
				String contactinfo = in.nextLine();
				System.out.println("Enter Martyr #"+i+" Role (mom,dad,son,daughter)");
				String role = in.nextLine();
				System.out.println("Enter Date Of Martyrdom : ");
				String DateOfMartyrdom =in.nextLine();
				System.out.println("Enter Cause Of Death : ");
				String CauseOfDeath=in.nextLine();
				System.out.println("Enter Place Of Death : ");
				String PlaceOfDeath = in.nextLine();
		
				Martyr martyr = new Martyr(ID, name, age,gender, addres,
				contactinfo , DateOfMartyrdom,CauseOfDeath , PlaceOfDeath) ;
				family.addMember(martyr,role);
	            System.out.println("Added martyr successfully!");
	           
	            System.out.println("Do you want to add another martyr? (yes/no): ");
                String addAnother = in.nextLine();
                if (!addAnother.equalsIgnoreCase("yes")) {
                    break;
                }
			  }
			}
	      }
		  
		  System.out.println("Family Not Found!");
		
		} catch (IllegalArgumentException e) {
            System.out.println("Error : " + e.getMessage());
           
        }		
	}

	
	// a method from me to add a members
	public LivePerson forMember()  {
		try {
	        Scanner in = new Scanner(System.in);
	        System.out.println("Enter the Name of Family: ");
	        String familyName = in.nextLine();

	        for (Family family : families) {
	          if (family.getFamilyName().equals(familyName)) {
	             int i;
	               for (i = 1; i <= 100; i++) {
	                 System.out.println("Enter ID for Member #" + i + " : ");
	                 String ID = in.nextLine();
	                 System.out.println("Enter Member #" +i+ " Name : ");
	                 String name = in.nextLine();
	                 System.out.println("Enter Member #" +i+ " Age : ");
	                 int age = in.nextInt();
	                 System.out.println("Enter Member #" +i+ " Gender : ");
	                 in.nextLine();
	                 String gender = in.nextLine();
	                 System.out.println("Enter Member #" +i+ " Address : ");
	                 String address = in.nextLine();
	                 System.out.println("Enter Member #" +i+ " Contact Info: ");
	                 String contactInfo = in.nextLine();
	                 System.out.println("Enter Member #" +i+ " Role (mom, dad, son, daughter): ");
	                 String role = in.nextLine();
	                 LivePerson person = new LivePerson(ID, name, age, gender, address, contactInfo);
	                 family.addMember(person, role);  
	                 System.out.println("Do you want to add another member? (yes/no): ");
	                 String addAnother = in.nextLine();
	                 if (!addAnother.equalsIgnoreCase("yes")) {
	                      break;
	                 }
	              }
	              return null; //return null after the loop
	           }
	        }

	        System.out.println("Family Not Found!");

	    } catch (IllegalArgumentException e) {
	        System.out.println("Error: " + e.getMessage());
	    }
	    return null;
	}
	
	// this method called another method to sort the families by martyr and then print it in File
	public void WriteAllFamilies(String file) {
		try {
	        File f = new File(file);
	        PrintWriter pw = new PrintWriter(f);
	        Family fam = new Family();
	        ArrayList<Family> sortedFamilies = fam.sortByMartyrs(families);

	        for (Family family : sortedFamilies) {
	            pw.println("Family " + family.getFamilyName() + " : have (" + family.countMartyrs() + ") Martyr");

	            // print parents details
	            for (Person parent : family.getParent()) {
	                pw.println("Parent: " + parent.toString());
	            }

	            // print members details
	            for (Person member : family.getMembers()) {
	                pw.println("Member: " + member.toString());
	            }

	            pw.flush();
	        }
	        System.out.println("All families data written to file successfully.");
	        pw.close();
	    } catch (FileNotFoundException e) {
	        System.err.println("Error writing all families to file: " + e.getMessage());
	    }
	}
	
	
	// This Method To read a Families form a File
	public boolean editFamilyFromFile(String fromFile) {
		try {
			File file = new File(fromFile);
			Scanner in = new Scanner(file);

            while (in.hasNextLine()) {
               String[] familyData = in.nextLine().split(",");
               Family family = new Family(familyData[0]);

               //check if the family was exists
               boolean familyExists = false;
               for (Family existingFamily : families) {
            	   if (existingFamily.getFamilyName().equals(family.getFamilyName())) {
            		   family = existingFamily; 
                       familyExists = true;
                       break;
                    }
               }

               if (!familyExists) {
                   families.add(family); // add a new family
               }

               System.out.println("Family Data Length: " + familyData.length);
               System.out.println("Family Data Content: " + Arrays.toString(familyData));
               
               // here we need to check if the member i need to read it is member (live) or martyr
               if (familyData.length >= 8) {       
                  String liveOrMartyr = familyData[1];
                  String ID = familyData[2];
                  String name = familyData[3];
                  int age = Integer.parseInt(familyData[4]);
                  String gender = familyData[5];
                  String address = familyData[6];
                  String contactInfo = familyData[7];
                  String role = familyData[8];
                  
               // here >=8 because the number of details to a member live is 7 + family + نوعه =9    
                  if (liveOrMartyr.equals("member") && familyData.length >= 9) {
                      Person person = new LivePerson(ID, name, age, gender, address, contactInfo);
                      family.addMember(person, role);
                  } else if (liveOrMartyr.equals("martyr") && familyData.length >= 12) {
                        String dateOfMartyrdom = familyData[9];
                        String causeOfDeath = familyData[10];
                        String placeOfDeath = familyData[11];
                        Person person = new Martyr(ID, name, age, gender, address, contactInfo, dateOfMartyrdom, causeOfDeath, placeOfDeath);
                        family.addMember(person, role);
                  }
              }
            }
            in.close();
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("Error reading family data from file: " + e.getMessage());
            return false;
        }
	}
	
}
