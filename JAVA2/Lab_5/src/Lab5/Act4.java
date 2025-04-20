package Lab5;
import java.io.FileInputStream;
import java.io.IOException;

public class Act4 {

    public static void main(String[] args) {
    	
        String fileName = "data.dat";
        int sum = 0;
     

        try {
            FileInputStream fis = new FileInputStream(fileName);
            
            int value;
            while ( (value = fis.read()) != -1 ) {
            	sum += value;
            }
            
            System.out.println("The sum of the integers is: " + sum);
            
        } catch (IOException e) {
            e.getMessage();
        }
    }

}
