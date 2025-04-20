package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Queue;

			/*
  				# Read File and Create Huffman Codes
			 */
	
public class CreateHuffman {

    private static Huffman[] table = new Huffman[256];
    private static Node root;

    private int[] array;
    private int size;

	// Traverse Huffman Tree to get huffman codes
    public static void Traversal(Node node, String code, Huffman[] huffmanCodeArray) {
        if (node == null) 
        	return; 						// if the file is Empty, the root can be zero

        if (!node.isLeaf()) {
            // recursively traverse left and right subtrees
            Traversal(node.getLeft(), code + '0', huffmanCodeArray);
            Traversal(node.getRight(), code + '1', huffmanCodeArray);
        } else {
            // leaf node : create Huffman code and add to table
            Huffman huffmanResult = new Huffman(node.getAscii(), code, node.getFreq(), code.length());
            huffmanCodeArray[node.getAscii()] = huffmanResult;
        }
    }

    // Create a header (thats create in Pre-Order )
    public static void createHeader(List<Integer> list, List<Integer> finalList, Node root) {
        if (root == null) 
        	return;

        if (root.isLeaf()) {
        
        	list.add(1);								 // add 1 to list and ASCII value to finalList
            finalList.add(root.getAscii());

        } else {
        	
            list.add(0);									            // add 0 to list and recurse
            createHeader(list, finalList, root.getLeft());				// build it in pre-order
            createHeader(list, finalList, root.getRight());
        }
    }

    // Build Huffman Tree from queues after reading the header
    public static Node build(Queue<Node> queue, Queue<Node> finalQueue, Node root) {
       
    	if (!queue.isEmpty()) {
    		Node temp = queue.poll();

            if (temp.getAscii() == 0) { 				// if it's not leaf
        
            	root = temp;
                root.setLeft(build(queue, finalQueue, root.getLeft()));
                root.setRight(build(queue, finalQueue, root.getRight()));
            
            } else { 									// if it's Leaf 
                return finalQueue.poll();
            }
        }
        return root;
    }


    // Read File before compressing to calculate the frequency by buffer (8 byte)
 	// and using channel
    public void readFile(File file) throws IOException {			 
        array = new int[256];										 // Frequency array to calculate the frequencies
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel channel = inputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(8);

        int numOfBytes;
        while ((numOfBytes = channel.read(buffer)) != -1) {
            buffer.flip();
            for (int i = 0; i < numOfBytes; i++) {
                int b = buffer.get();
                if (b < 0) 
                	b += 256; // Convert signed byte to unsigned
                array[b]++;
            }
            buffer.clear();
        }
        inputStream.close();
    }

	// by filling the heap, then we make huffman tree
    public void buildHuffManTree() {
        if (array == null)
        	return;

        Heap heap = new Heap(257);

        // Insert characters with non zero frequency into the heap
        for (int i = 0; i < array.length; i++) {			// add the "existing" chars of the file ==> to the "heap"
            if (array[i] != 0) {
                heap.add(new Node(i, array[i]));
                size++;
            }
        }

        // creating the Huffman tree
        for (int i = 1; i < size; i++) {
        	Node z = new Node();
            Node a = heap.removeMin();
            Node b = heap.removeMin();

            z.setLeft(a);
            z.setRight(b);
            z.setFreq(a.getFreq() + b.getFreq());

            heap.add(z);
        }

        root = heap.getMin(); 					// Root of the Huffman tree
        Traversal(root, "", table); 			// to get and save the codes in the huffman table
    }

    public Huffman[] getTable() {
        return table;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        CreateHuffman.root = root;
    }
}
