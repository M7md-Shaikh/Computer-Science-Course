package Lab5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitOutputStream {
	
	private FileOutputStream fos ;
	private int buffer ;
	private int numBits;
	
	
	public BitOutputStream(File file) throws FileNotFoundException{
		this.fos = new FileOutputStream(file);
		this.buffer=0;
		this.numBits = 0;
	}
	
	public void writeBit(char bit) throws IOException{
		if ( bit != '0' && bit != '1') {
			throw new IllegalArgumentException("Invalid bit: " + bit);
		}
		int b = bit - '0';
		buffer =((buffer << 1 ) | b );
		numBits++;
		
		if (numBits == 8){
			fos.write(buffer);
			buffer =0 ;
			numBits = 0;
		}
	}
	
	
	public void writeBit(String bit) throws IOException {
		 for (int i = 0; i < bit.length(); i++) {
		 writeBit(bit.charAt(i));
		 }
	}
	
	public void close() throws IOException {
		while (numBits != 0) {
			 writeBit('0');
		}
		fos.close();
		
	}
	
	public static void main(String [] args) throws IOException {
		
		File file = new File ("output.bin");
		BitOutputStream bos = new BitOutputStream(file);
		
		bos.writeBit('1');
		bos.writeBit("0101");
		bos.writeBit("11001100");
		
		bos.close();
		
	}
}
