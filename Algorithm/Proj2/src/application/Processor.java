package application;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Processor {
    
    // Common attributes
	List<Integer> list = new List<>();
	Huffman[] table;
	String stat;
	private Node root;
    private String extension;
    private double ratio;
    private List<Integer> finalList;
	private String ext = "";
	private File newDeCompressedFile;
	private int index;


	public Processor() {};
    
    // Constructor for shared initialization
    public Processor(Huffman[] table, Node root) {
        this.table = table;
        this.root = root;
    }

    public void compress(File inFile) throws IOException {

        String path = inFile.getAbsolutePath().substring(0, inFile.getAbsolutePath().lastIndexOf('\\') + 1);

        FileInputStream inputStream = new FileInputStream(inFile);
        int index = inFile.getName().lastIndexOf('.');
        String extensionOfInputFile = inFile.getName().substring(index + 1);
        File outFile = new File(path + inFile.getName().substring(0, index) + ".huff");

        FileOutputStream outputStream = new FileOutputStream(outFile);

        BufferedInputStream bufferIn = new BufferedInputStream(inputStream); // from the file
        BufferedOutputStream bufferOut = new BufferedOutputStream(outputStream); // to the file


        long previousSize = inFile.length();


        writeHeader(bufferOut, extensionOfInputFile);   //Write Header To file
        

        int available;
        String s = "", temp = "";

        available = bufferIn.available();

        byte[] array;
        List<Integer> output = new List<>();

        if (available > 8) {
            array = new byte[8];

        } else {
            array = new byte[available];
        }

        while (available > 0) { //Reading All file
            bufferIn.read(array);
            for (int i = 0; i < array.length; i++) { //Read Array unsigned And get the huffman code from table then add it to string,

            	// which makes the whole file data into one (compressed(huffman codes)) string
                int value = unsignedToBytes(array[i]);
                s += table[value].getHuffman(); // get the code of each byte(char) from the generated table;
            }

            if (s.length() >= 64) {  //If we reach 8 bytes

            	// int count = 0;
                while (s.length() >= 8) { // Substring bits to 8 by 8 then add it to output list
                    temp = s.substring(0, 8);
                    s = s.substring(8);

                    int integerValue = Integer.parseInt(temp, 2);   //get the integer value from binary's
                    output.add(integerValue);
                }

                for (int i = 0; i < output.size(); i++) {   //Write list to output
                    bufferOut.write(output.get(i));
                }
                output.clear();
            }
            
            available = bufferIn.available();
            if (available > 8) {
                array = new byte[8];
            } else {
                array = new byte[available];
            }
        }

        //If there still bits in string
        if (!s.equals("")) {
            while (s.length() >= 8) { //substring bits 8 by 8 then to output
                temp = s.substring(0, 8);
                s = s.substring(8);

                int integerValue = Integer.parseInt(temp, 2);
                output.add(integerValue);

            }

            //If there still bits add them .... Then add the length of bits of the last char
            if (!s.equals("")) {

            	temp = s;
                int integerValue = Integer.parseInt(temp, 2);
                output.add(integerValue);
                output.add(s.length());
            
            } else
            	output.add(8);
            
            if (!output.isEmpty()) //Add output list to output
                for (int i = 0; i < output.size(); i++) {
                    bufferOut.write(output.get(i));
                }
        }

        bufferIn.close();
        bufferOut.close();
        inputStream.close();
        outputStream.close();

        this.ratio = (float) outFile.length() / (previousSize*100);  //Ratio of Compressing

        stat = "File Path " +inFile.getAbsolutePath()+"\nFile Size: " + previousSize + " Bytes"+"\nCompresses File Path : "
                + outFile.getAbsolutePath()+ "\nComppressed File Size: " + outFile.length() + " Bytes"+ 
                        "\nRatio of compression : " +this.ratio;

    }


    
    private void writeHeader(BufferedOutputStream bufferOut, String extensionOfInputFile) throws IOException {

    	List<Integer> listBinary = new List<>(); 				//Tree Nodes :exp) [0,1,0,0,1,1,0,0,1,1,1]
    	List<Integer> finalList = new List<>(); 				 //leaf characters:exp) [A,C,B,F,E,D]

        CreateHuffman.createHeader(listBinary, finalList, root);  //Read Tree to binary and leafs
        this.finalList = finalList;
        list.add(extensionOfInputFile.length() - 1);				 //Add it to list
        for (int i = 0; i < extensionOfInputFile.length(); i++) { 	//Add the char of extension name

            list.add((int) (extensionOfInputFile.charAt(i)));

        }

        int counter = 0;
        this.index = list.size(); // the index the size of the nodes (0s,1s)
        String b = "", temp;

        // this is to fill the listBinary(from huffman "createHeader()" method)
        for (int i = 0; i < listBinary.size(); i++) {    				//Get All binary's in one string
            b += listBinary.get(i);

        }

        //from the above loop ==> as each 8 numbers(0s,1s) => are put into one integer
        while (b.length() >= 8) {    								//substring this list to 8 by 8 bits

            temp = b.substring(0, 8);
            b = b.substring(8);
            counter++;

            list.add(Integer.parseInt(temp, 2));					 // take and convert the temp string into a binary "byte"
        }


        //If there still bits !
        if (!b.equals("")) {

            temp = b;
            ++counter;

            list.add(Integer.parseInt(temp, 2));
            list.add(b.length());  //add the last char bits length

        } else {

            list.add(8);

        }


        list.insert(index, counter - 1);       //Add the number of char to read (Tree binary's Nodes)
        list.add(finalList.size() - 1);    //Add the number of char to read(Tree Leafs char)

        for (int i = 0; i < finalList.size(); i++) {

            list.add(finalList.get(i));
        }


        for (int i = 0; i < list.size(); i++) {   //Write it to output
            bufferOut.write(list.get(i));
            //System.out.println(list.get(i).intValue() );
        }


    }


    // Shared utility methods (e.g., unsignedToBytes, getBinary)
    public static int unsignedToBytes(byte a) {
        return a & 0xFF;
    }
    

 // Convert integer(decimal number) to binary
 	public static StringBuffer getBinary(int decNum) {

 		StringBuffer sBuf = new StringBuffer();

 		int temp;
 		while (decNum > 0) {

 			temp = decNum % 2;
 			sBuf.insert(0, temp);
 			decNum = decNum / 2;

 		}

 		return sBuf;
 	}

    public File getNewDeCompressedFile() {
		return newDeCompressedFile;
	}

	public void decompress(File f) throws IOException {

		FileInputStream in = new FileInputStream(f);
		BufferedInputStream bufferIn = new BufferedInputStream(in);

		byte[] array;

		readHeader(in); // Reading header from file

		String path = f.getAbsolutePath().substring(0, f.getAbsolutePath().lastIndexOf('\\') + 1);

		newDeCompressedFile = new File(path + f.getName().split("[.]")[0] + "(1)" + "." + ext);
		FileOutputStream out = new FileOutputStream(newDeCompressedFile);

		BufferedOutputStream bufferOut = new BufferedOutputStream(out);

		// READ FILE --> OUTPUT FILE

		String s = "";
		int available = bufferIn.available();
		List<Integer> output = new List<>();

		while (available > 0) {

			if (available > 8) {

				array = new byte[8];

				bufferIn.read(array);

				for (int i = 0; i < array.length; i++) { // Convert each char to binary then check if it's 8 bits

					int value = unsignedToBytes(array[i]);

					StringBuffer buf = getBinary(value);

					while (buf.length() < 8) { // adding 0s if it's less than 8 bits

						buf.insert(0, 0);

					}

					s += buf.toString();

				}

			} else { // If available less than 8 bytes

				array = new byte[available];

				bufferIn.read(array);

				for (int i = 0; i < array.length - 1; i++) {

					int value = unsignedToBytes(array[i]); // Last char to get number of bits

					if (i == array.length - 2) {

						int len = array[i + 1];

						StringBuffer temp = getBinary(array[i]);

						while (temp.length() < len) {
							temp.insert(0, 0);

						}

						s += temp.toString();

					} else {

						StringBuffer buf = getBinary(value);

						while (buf.length() < 8) {

							buf.insert(0, 0);

						}

						s += buf.toString();
					}

				}

			}

			if (s.length() >= 64) {// If we reach 4 bytes then to output

				StringBuffer buf = new StringBuffer(s);

				while (buf.length() > 8) { // substring to 8 by 8 bits

					int integerValue = getChar(buf); // get char value from tree

					output.add(integerValue);
				}

				s = buf.toString();

				for (int i = 0; i < output.size(); i++) {

					bufferOut.write(output.get(i));

				}

				output.clear();

			}

			available = bufferIn.available();

		}

		StringBuffer buf = new StringBuffer(s);

		// If there still bits
		if (!s.equals("")) {

			while (buf.length() >= 8) {

				int integerValue = getChar(buf);

				output.add(integerValue);

			}
		}

		// If still bits
		if (!(buf.toString().isEmpty())) {

			int integerValue = getChar(buf);

			output.add(integerValue);

		}

		for (int i = 0; i < output.size(); i++) {

			bufferOut.write((output.get(i)));

		}

		bufferIn.close();
		bufferOut.close();

		in.close();
		out.close();

	}

	// Get original char from tree
	public int getChar(StringBuffer s) {

		Node pos = root;
		int i = 0;

		while (pos.getLeft() != null && pos.getRight() != null && i < s.length()) {

			if (s.charAt(i) == '0') {
				pos = pos.getLeft();
				++i;
			} else {
				pos = pos.getRight();
				++i;
			}
		}

		s.delete(0, i);

		return pos.getAscii();

	}

	public void readHeader(FileInputStream in) throws IOException {

		int cc;

		int counter = 0;

		cc = in.read();

		byte[] array = new byte[cc + 1]; // Reading number of char in extension

		List<Integer> listBinary = new List<>();
		List<Integer> finalList = new List<>();

		while ((cc = in.read(array)) != -1) {

			if (counter == 0)
				for (int i = 0; i < array.length; i++) { // Reading Extension
					int value = unsignedToBytes(array[i]);
					ext += (char) value;
				}

			if (counter == 1) { // Reading Nodes in tree
				for (int i = 0; i < array.length; i++) {
					int value = unsignedToBytes(array[i]);
					listBinary.add(value);
				}
			}

			if (counter == 2) { // Reading char of tree

				for (int i = 0; i < array.length; i++) {

					int value = unsignedToBytes(array[i]);

					finalList.add(value);

				}
			}

			++counter;

			if (counter == 3)
				break;

			if (counter == 1) {

				cc = in.read();

				array = new byte[cc + 2];

			} else {

				cc = in.read();
				array = new byte[cc + 1];

			}
		}

		// Create tree

		Queue<Node> queue = new LinkedList<>();
		Queue<Node> finalQueue = new LinkedList<>();

		// Create Queue of char
		for (int i = 0; i < finalList.size(); i++) {

			finalQueue.add(new Node(finalList.get(i), 0));

		}

		String s = "";
		StringBuffer temp;

		// Convert Reading char to binary
		for (int i = 0; i < listBinary.size() - 1; i++) {

			if (i == listBinary.size() - 2) { // The last char show number of bits

				int len = listBinary.get(i + 1);

				temp = getBinary(listBinary.get(i));

				while (temp.length() < len) {
					temp.insert(0, 0);
				}

				s += temp.toString();

			} else {

				temp = getBinary(listBinary.get(i));

				while (temp.length() < 8) {

					temp.insert(0, 0);

				}

				s += temp.toString();

			}

		}

		// Create Queue of binary's
		for (int i = 0; i < s.length(); i++) {

			if (s.charAt(i) == '1')
				queue.add(new Node(1, 0));

			else if (s.charAt(i) == '0')
				queue.add(new Node(0, 0));

		}

		// Create Tree
		root = CreateHuffman.build(queue, finalQueue, null);

		// Create table
		CreateHuffman.Traversal(root, "", this.table);

	}


}
