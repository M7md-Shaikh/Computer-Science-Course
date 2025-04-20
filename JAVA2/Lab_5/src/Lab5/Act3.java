package Lab5;
import java.io.*;
import java.util.Random;

public class Act3 {

    public static void main(String[] args)  {
        String fileName = "data.dat";
        
        try (FileOutputStream fos = new FileOutputStream(fileName, false);
             DataOutputStream dos = new DataOutputStream(fos)) {
        	
            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                int randomInt = random.nextInt();
                dos.write(randomInt);
            }
            System.out.println("100 random integers successfully written to " + fileName);
        }
        catch(IOException e) {
        	e.getMessage();
        }
    }
    

}
