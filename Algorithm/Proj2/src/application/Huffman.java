package application;

//class for table view final
public class Huffman {

	/*
	 * Huffman Node
	 */

	private int ascii;
	private String huffman;
	private int frequency;
	private int length;

	public Huffman() {

	}

	public Huffman(int ascii, String huffman, int frequency, int length) {

		this.ascii = ascii;
		this.huffman = huffman;
		this.frequency = frequency;
		this.length = length; // length of the "code"

	}

	@Override
	public String toString() {
		return "Huffman [ascii=" + ascii + ", Huffman=" + huffman + ", frequency=" + frequency + ", length=" + length
				+ "]";
	}

	public Integer getAscii() {
		return ascii;
	}

	public void setAscii(int ascii) {
		this.ascii = ascii;
	}

	public String getHuffman() {
		return huffman;
	}

	public void setHuffman(String huffman) {
		this.huffman = huffman;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
