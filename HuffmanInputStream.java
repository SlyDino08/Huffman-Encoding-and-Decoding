import java.io.*;
public class HuffmanInputStream {
 //add additional private variables and methods as needed
 //DO NOT modify the public method signatures or add public methods
	
//run configuration
private String tree;
private int totalChars;
 private DataInputStream d;
 private int byt = 0;
 private int bitCount = 8;
 private int[] bits = new int[8];
 	public HuffmanInputStream(String filename) {
 		try {
 			d = new DataInputStream(new FileInputStream(filename));
 			
 			//build tree
 			tree = d.readUTF();
 			totalChars = d.readInt();
 		}
 		catch (IOException e) {
 			System.out.println("error in huffman input stream");
 		}

 //add other initialization statements as needed
 }

 	public int readBit() {
//returns the next bit is the file
 //the value returned will be either a 0 or a 1
 		try {
 			//System.out.println(bitCount);
 			if(bitCount==8) {
 				byt = d.readUnsignedByte();
 				//System.out.println(byt);
 				for(int i = 7; i >= 0; i--) {
 					bits[i] = (int)byt % 2;
 					byt = byt/2;
 				}
 				bitCount = 0;
 			}	
 		}
 		catch (IOException e ) {
 			return -1;
 		}
		return bits[bitCount++];
 	}
 	public String getTree() {
 //return the tree representation read from the file
 		//use a stack and CharAt
 		return tree;
 	}
 	public int getTotalChars() {
	//return the character count read from the file
 		return totalChars;
 	}
}