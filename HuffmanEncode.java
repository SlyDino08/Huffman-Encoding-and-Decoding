import java.io.*;
import java.util.*;

public class HuffmanEncode {
public HuffmanEncode(String in, String out) {
//Implements the Huffman encoding algorithm
//Add private methods and instance variables as needed
	try {
		int index [] = new int [128];
		BinaryHeap priority;
		int size = 0;
		int totalChar = 0;
		BufferedReader n = new BufferedReader(new FileReader(in));
		int k = n.read();
		while(k != -1) {
			//System.out.println((char)k);
			totalChar++;
			index[k]++;
			k = n.read();
		}
		//n.close();
		for(int i = 0; i < index.length; i++) {
			if(index[i] > 0) {
				size++;
			}
		}
		priority = new BinaryHeap(size);
	
		for(int i = 0; i < index.length; i++) {
			if(index[i] > 0) {
				char c = (char)i;
				//System.out.println(c + " " + index[i]);
				priority.insert(index[i], new HuffmanTree(c));
			}
		}
		//System.out.println("test");
		while(priority.size > 1) {
			int l = priority.getMinPriority();
			HuffmanTree left = priority.getMinTree();
			priority.removeMin();
			int r = priority.getMinPriority();
			HuffmanTree right = priority.getMinTree();
			//System.out.println("right " + right.toString());
			//System.out.println("left " + left.toString());
			priority.removeMin();
			priority.insert(l+r, new HuffmanTree(right,left, (char)128));		
		}
		HuffmanTree tree = priority.getMinTree();
		String t = tree.toString();
		//System.out.println(t);
		HuffmanOutputStream hos = new HuffmanOutputStream(out,t, totalChar);
		BufferedReader q = new BufferedReader(new FileReader(in));
		String [] paths = getPaths(tree);
		while(q.ready()) {
			 writeBits((char)q.read(),paths,hos);
		}
		hos.close();
		n.close();
		q.close();
		System.out.println("done");
		
	}
	catch(IOException e){
		System.out.println("error in huffman encode");
	}
	
	
 }
private String[] getPaths(HuffmanTree tree) {
	String [] paths = new String[128];
	Iterator<String> t = tree.new PathIterator();
	while(t.hasNext()) {
		String s = t.next();
		paths[s.charAt(0)] = s.substring(2);
		
	}
	return paths;
}
private void writeBits(char c, String [] paths, HuffmanOutputStream hos) {

		for (int i = 0; i < paths[c].length(); i++) {
			//System.out.println(paths[c].charAt(i));
			hos.writeBit(paths[c].charAt(i));
		}
			
	
	
	
}


public static void main(String args[]) {
//args[0] is the name of the file whose contents should be compressed
//args[1] is the name of the output file that will hold the compressed
 //content of the input file
 new HuffmanEncode(args[0], args[1]);
 //do not add anything here
 }
}


/*
 * ask about what should be in the huffman output
 * how to read individual char using buffered reader
 * how to call to huffman output how and where to use the writeBit method.
 * best way to writeBits
 * best way to store paths;
 * 
 * 
 */ 


