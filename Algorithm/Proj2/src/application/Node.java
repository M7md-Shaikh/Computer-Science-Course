package application;

//this class to comparable the repetition and save ascii and make tree 
public class Node implements Comparable<Node> {

	// attributes
	private int ascii;
	private int freq;
	private Node left;
	private Node right;

	// constructor by ascii code and repetition
	public Node(int ascii, int freq) {
		this.ascii = ascii;
		this.freq = freq;
	}

	public Node() {
	}

	public int getAscii() {
		return ascii;
	}

	public void setAscii(int ascii) {
		this.ascii = ascii;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(freq, o.freq);
	}

	public boolean isLeaf() {
		return this.left == null && this.right == null;
	}
}
