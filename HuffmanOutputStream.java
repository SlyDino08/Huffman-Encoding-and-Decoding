import java.io.*;
public class HuffmanOutputStream {
 //add additional private variables as needed
 //do not modify the public methods signatures or add public methods

 DataOutputStream d;
 int bitCount = 0;
 int bite = 0;
 String byt = "";
 	public HuffmanOutputStream(String filename, String tree, int totalChars) {
 		try {
 			d = new DataOutputStream(new FileOutputStream(filename));
 			d.writeUTF(tree);
			d.writeInt(totalChars);
			//write bit private method
 		}
 		catch (IOException e) {
 			System.out.println("error in huffman output stream");
 		}
 //add other initialization statements as needed
 	}
 	public void writeBit(char bit) {
 //PRE:bit == '0' || bit == '1'
 		try {
 			int place = 7 - bitCount;
 			byt += bit;
 			if(bit == '1') {bite += Math.pow(2,place);}
 			bitCount++;
 			//System.out.println(bit + " " + bite + " " + bitCount + " ");
 			if(bitCount == 8) {
 				//System.out.println(byt);
 				byt = "";
 				d.writeByte(bite);
 				bitCount = 0;
 				bite = 0;
 				}
 		}
 		catch(IOException e) {
 			System.out.println("error in writeBit");
 		}
 	}

 	public void close() {
 //write final byte (if needed)
 //close the DataOutputStream
 		try {
 			if(bitCount < 8) {
 				//System.out.println(bite + " " + byt); 
 				d.writeByte(bite);
 			}
 			d.close();
 		}
 		catch(IOException e) {
 			System.out.println("error in close");
 		}
 	}
}