import java.io.*;
import java.util.*;
public class HuffmanDecode {
public HuffmanDecode(String in, String out) {
//implements the Huffman Decode Algorithm
//Add private methods and instance variables as needed
	
	try {
		HuffmanInputStream his = new HuffmanInputStream(in);
		HuffmanTree tree = new HuffmanTree(his.getTree(), (char)128);
		//System.out.println(" i " + tree.toString());
		BufferedWriter bw = new BufferedWriter(new FileWriter(out));
		PrintWriter pw = new PrintWriter(bw);
		boolean will = true;
		tree.moveToRoot();
		while(will) {
			int x = his.readBit();
			//System.out.println(x);
			if(x == 0) {
				tree.moveToLeft();
			}
			if(x == 1) {
				tree.moveToRight();
			}
			if(tree.atLeaf()) {
				//System.out.println("at leaf");
				pw.print(tree.current());
				tree.moveToRoot();
			}
			if(x == -1) {
				will = false;
			}
		}
		pw.close();
		bw.close();
		System.out.println("done");
		
	}
	catch (IOException e ) {
		System.out.println("Error in huffman Decode");
	}
 }
public static void main(String args[]) {
//args[0] is the name of a input file (a file created by Huffman Encode)
//args[1] is the name of the output file for the uncompressed file
 new HuffmanDecode(args[0], args[1]);
 //do not add anything here
 }
}