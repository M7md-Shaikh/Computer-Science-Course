package Lab4;

import java.io.*;
import java.util.*;

public class Driver {

    public static void main(String[] args) throws IOException {

    	String filename = "Tawjihi.dat";
    	try {
    		
    	    DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename));

    	    dos.writeInt(1); 
    	    dos.writeUTF("Ali Mosa"); 
    	    dos.writeChar('M'); 
    	    dos.writeUTF("Ramallah School"); 
    	    dos.writeUTF("Scientific"); 
    	    dos.writeInt(12345); 
    	    dos.writeInt(2022); 
    	    dos.writeInt(2); 
    	    dos.writeUTF("Math"); 
    	    dos.writeUTF("Core"); 
    	    dos.writeInt(95); 
    	    dos.writeInt(100);
    	    dos.writeUTF("Physics"); 
            dos.writeUTF("Core");
    	    dos.writeInt(90); 
            dos.writeInt(100); 
            
            dos.writeInt(1); 
    	    dos.writeUTF("Suzan Amr"); 
    	    dos.writeChar('F'); 
    	    dos.writeUTF("Ramallah School"); 
    	    dos.writeUTF("Literary"); 
    	    dos.writeInt(56789); 
    	    dos.writeInt(2023); 
    	    dos.writeInt(2); 
    	    dos.writeUTF("Sciences"); 
    	    dos.writeUTF("Core"); 
    	    dos.writeInt(91); 
    	    dos.writeInt(100);
    	    dos.writeUTF("Geography"); 
            dos.writeUTF("Core");
    	    dos.writeInt(94); 
            dos.writeInt(100); 
    	     
    	    dos.close();
    	    
    	    DataInputStream dis = new DataInputStream(new FileInputStream(filename));

    	    while (dis.available() > 0) {
                int id = dis.readInt();
                String name = dis.readUTF();
                char gender = dis.readChar();
                String school = dis.readUTF();
                String branch = dis.readUTF();
                int seatingNum = dis.readInt();
                int year = dis.readInt();
                int numOfSubjects = dis.readInt();

                System.out.println("ID : " +id);
                System.out.println("Name : "+ name);
                System.out.println("Gender : "+gender);
                System.out.println("School : " +school);
                System.out.println("Branch : "+ branch);
                System.out.println("Seating Number : " +seatingNum);
                System.out.println("Year: " + year);
                System.out.println("Number of Subjects : " + numOfSubjects);

                for (int i = 0; i < numOfSubjects; i++) {
                    String subTitle = dis.readUTF();
                    String subType = dis.readUTF();
                    int subMark = dis.readInt();
                    int subMaxMark = dis.readInt();

                    System.out.println("Subject " +(i+ 1)+ " Title : " + subTitle);
                    System.out.println("Subject " +(i+1)+ " Type : " + subType);
                    System.out.println("Subject " +(i+1)+ " Mark : " + subMark);
                    System.out.println("Subject " +(i +1)+ " Max Mark : " + subMaxMark);
                }
                System.out.println();
            }

            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}