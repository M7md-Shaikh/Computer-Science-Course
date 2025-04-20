package gaza;

import java.util.ArrayList;
import java.util.Scanner;

public class Family implements Sortable , Cloneable{
	
	private String familyName;
	private ArrayList<Person> members;
	private ArrayList<Person> parents;
	
	public Family() {
		this.members =new ArrayList<>() ;
		this.parents = new ArrayList<>();
	}
	
	public Family(String familyName){
		this.familyName = familyName;
		this.members =new ArrayList<>() ;
		this.parents = new ArrayList<>();
	}
	
	
	// method can added members to members and parents array , and do that cant recive more than two perants
	public boolean addMember(Person member, String role)  {
	  try {
		  
		  // this custom Exception (SameIDException) i created for check neither have the same id 
		  if (hasDuplicateID(member)) {
	            throw new SameIDException("Duplicate ID found in the family.");
	        }
		  
		  // we should just enter one of the role
		 if (!(role.equals("mom"))&& !(role.equals("dad"))&&!(role.equals("son"))&& !(role.equals("daughter"))) {
	            throw new IllegalArgumentException("Invalid role: " + role);
	      }

		 
	    // Check if the family has a mom or dad
		 //this custom Exception (ParentsLimitException) i created for check if family dosen't have more 2 parent
	     if ((role.equals("mom")|| role.equals("dad")) && parents.size() >= 2) {
	    	System.out.println("This family already has a " + (role.equals("mom") ? "mom" : "dad"));
	    	throw new ParentsLimitException("Parents limit exceeded . Only two parents allowed ");
	     }
	    
	     // if role is parents , add member to parents array 
	     if (role.equals("mom") || role.equals("dad")) {
	            parents.add(member);
	            System.out.println("Parent added successfully.");
	        } else {
	            if (parents.size() >= 2) {
	                System.out.println("Member added successfully.");
	            } else {
	       		 //this custom Exception (NoAddingParentsException) i created until user can't add a son/daughter before mom and dad
	                throw new ParentsDoesntAddingException("Parents must be added before other family members.");
	            }
	        }
	     
	     // Add the member to the members list
	     members.add(member);
	     
	     return true;
	     
	   } catch (IllegalArgumentException e) {
            System.out.println("Error : " + e.getMessage());
            return false;
       }catch( ParentsDoesntAddingException| ParentsLimitException e) {
		   System.out.println("Error : " + e.getMessage());
		   return false;
	   } catch (SameIDException e) {
		   System.out.println("Error : " + e.getMessage());
		   return false;
	   }
	}
	
	 // a method can delete (remove) a members by the information about he
	 public boolean removeMember(Person member) {
			if (members.contains(member)) {
				members.remove(member);		
			}
			if (parents.contains(member)) {
				parents.remove(member);
				return true;
			}	
		return false;	
	}
	
	
	public ArrayList<Person> getMembers(){
		return members;
	}
	
	
	public void addParent(Person parent) {
	    if (!parents.contains(parent)) {
	        if (parent.getGender().equalsIgnoreCase("fmale") ) 
	            System.out.println("This family already has mom, Cannot add more.");
	        
	        else if (parent.getGender().equalsIgnoreCase("male") ) 
	            System.out.println("This family already has dad, Cannot add more.");
	        
	        else {
	            parents.add(parent);
	            System.out.println("Parent added successfully.");
	        }
	    } 
	    else 
	        System.out.println("This person is already a parent in the family.");
	}
	
	
	public String getFamilyName() {
		return familyName;
	}
	
	
	public void setFamilyName(String familyName) {
		this.familyName=familyName;
	}
	
	
	
	public boolean removeParent(Person parent) {
		if (parents.contains(parent)) {
			parents.remove(parent);
			return true;
		}
		return false;
	}
	
	
	public ArrayList<Person> getParent(){
		return parents;
	}
	
	
	@Override
	public String toString() {
		return "Family [FamilyName : " + familyName + ", Members : " + members + 
				", Parents : " + parents + "]";
	}
	

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }

	    if (obj == null) {
	        return false;
	    }

	    Family otherFamily = (Family) obj;
	        
	    // compare between two families for number of martyrs
	    if (true) {
	    	return familyName.equals(otherFamily.familyName) && countMartyrs() == otherFamily.countMartyrs();
	    }
	    return false;
	}
	
	// this method to calculate a number of martyr for each family
	@Override
	public int countMartyrs() {
		    int totalMartyrs = 0;
		    for (Person person : members) {
		        if (person instanceof Martyr) {
		            totalMartyrs++;
		        }
		    }
	    return totalMartyrs;	
	}	
	
	
	// this method has arrays for sorted a families by number of Martyr (sort is desc)
	@Override
	public  ArrayList<Family> sortByMartyrs(ArrayList<Family> families) {
		
        ArrayList<Family> sortedFamilies = new ArrayList<>(families);

        for (int i = 0; i < sortedFamilies.size() - 1; i++) {
            for (int j = 0; j < sortedFamilies.size() - i - 1; j++) {
                if (sortedFamilies.get(j).countMartyrs() < sortedFamilies.get(j + 1).countMartyrs()) {
                    Family temp = sortedFamilies.get(j);
                    sortedFamilies.set(j, sortedFamilies.get(j + 1));
                    sortedFamilies.set(j + 1, temp);
                }
            }
        }
        
        if (!sortedFamilies.isEmpty()) {       // check if the array is empty
            System.out.println("Families sorted by martyrs count:");
            for (Family family : sortedFamilies) {
                System.out.println("Family " + family.getFamilyName() + ": " + family.countMartyrs() + " martyrs");
            }
        } else {
            System.out.println("No families to print.");
        }
        return sortedFamilies; 
	}
	
	
	// this method to calculate a number of martyr for each family
	 @Override
	 public int countOrphans() {
		int totalOrphans = 0;
	    boolean hasMartyrMom = false;
	    boolean hasMartyrDad = false;
	    for (Person member : getMembers()) {
            if (member instanceof Martyr) {
                // to show if the martyr is a parent
                if (getParent().contains(member)) {
                    if (member.getGender().equalsIgnoreCase("fmale")) {
                        hasMartyrMom = true;
                    } else if (member.getGender().equalsIgnoreCase("male")) {
                        hasMartyrDad = true;
                    }
                }
            } 
            // to check if the live person is an orphan
            if (!getParent().contains(member) &&( hasMartyrMom == true && hasMartyrDad == true)) {
                totalOrphans++;
            }
        }
     return totalOrphans;
	 }
	

	// this method has arrays for sorted a families by number of Martyr (sort is desc)
	@Override
	public ArrayList<Family> sortByOrphans(ArrayList<Family> families) {
		 ArrayList<Family> sortedFamilies = new ArrayList<>(families);

	        for (int i = 0; i < sortedFamilies.size() - 1; i++) {
	            for (int j = 0; j < sortedFamilies.size() - i - 1; j++) {
	                if (sortedFamilies.get(j).countOrphans() < sortedFamilies.get(j + 1).countOrphans()) {
	                    Family temp = sortedFamilies.get(j);
	                    sortedFamilies.set(j, sortedFamilies.get(j + 1));
	                    sortedFamilies.set(j + 1, temp);
	                }
	            }
	        }
	        
	        if (!sortedFamilies.isEmpty()) {       // check if the array is empty
	            System.out.println("Families sorted by Orphans count:");
	            for (Family family : sortedFamilies) {
	                System.out.println("Family " + family.getFamilyName() + ": " + family.countOrphans() + " orphans");
	            }
	        } else {
	            System.out.println("No families to print.");
	        }
	        return sortedFamilies;
	}
	
	// clone method for a deep copy
	@Override
	public Family clone() {
	    try {
	        Family cloneFamily = (Family) super.clone();
	        cloneFamily.members = new ArrayList<>();
	        cloneFamily.parents = new ArrayList<>();

	        for (Person member : this.members) {
	            cloneFamily.members.add(member.clone());
	        }

	        for (Person parent : this.parents) {
	            cloneFamily.parents.add(parent.clone());
	        }

	        return cloneFamily;
	    } catch (CloneNotSupportedException e) {
	        System.out.println(e.getMessage());
	        return null;
	    }
	}

	
	private boolean hasDuplicateID(Person person) {
        String newPersonID = person.getID();

        for (Person exPerson : members) {
            if (exPerson.getID().equals(newPersonID)) {
                return true; 
            }
        }
        return false; 
    }
	
}
