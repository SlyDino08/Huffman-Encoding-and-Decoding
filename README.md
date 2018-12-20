# Huffman-Encoding-and-Decoding
uses Huffman trees and binary heaps to losslessly compress txt documents and decompress them

huffman encode takes a txt file as a command line argument. first it uses a buffered reader to iterate through
the file and find the frequency of each character in the file. Storing each character in an array indexed by character
after reading through the file and incrementing the frequency array each character the character is made into a huffman tree and
its frequency and huffman tree are inserted into 2 parallel binary heaps based on frequency. the binary heap is pop twice and combined
with a empty character as a parent and the frequncies are added then inserted back into binary heaps. once there is only 1 item left
in the heap that is the fina huffman tree. an iterator then goes to each leaf node and stores the path through
the tree to each of the leaf characters (0 for left, 1 for right) in an array indexed by character. the buffered read iterates through the file
again and finds the path to each character and writes it to the file in 8 bit increments using writeBit in the huffman output stream

huffman decode takes in an huffman encoded txt file and a constructs a tree from the post order string representation.
it then iterates through each byte of the file and uses it to navigate the tree once it finds a leaf node it writes it to the file and returns to the root.
this continues until the file has been read completely.
