package Lab5;
import java.util.Scanner;

public class Act2 {

    public static int hex2Dec(String hexIn) throws HexFormatException {
        int decimal = 0;
        for (int i = hexIn.length() - 1; i >= 0; i--) {
            char hexCh = hexIn.charAt(i);
            if (hexCh >= 'A' && hexCh <= 'F') {
                decimal += (10 + hexCh - 'A') * Math.pow(16, hexIn.length() - 1 - i);
            } else if (hexCh >= '0' && hexCh <= '9') {
                decimal += (hexCh - '0') * Math.pow(16, hexIn.length() - 1 - i);
            } else {
                throw new HexFormatException("Invalid number");
            }
        }
        return decimal;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Please Enter a hex number: ");
        String hex = in.nextLine();
        try {
            int decimal = hex2Dec(hex);
            System.out.println("The decimal is: " + decimal);
        } catch (HexFormatException e) {
            System.out.println("Not a hex Number!");
        }
    }
}