import java.util.*;

public class HuffmanTree {
//DO NOT include the frequency or priority in the tree
private class Node {
	private Node left;
	private char data;
	private Node right;
	private Node parent;
	private Node(Node L, char d, Node R, Node P) {
		left = L;
		data = d;
		right = R;
		parent = P;
	}
 }
	private Node root;
	private Node current; //this value is changed by the move methods
	public HuffmanTree() {
		root = null;
		current = null;
	}
	public HuffmanTree(char d) {
//makes a single node tree
		root = new Node(null,d,null,null);
	}
	public HuffmanTree(String t, char nonLeaf) {
//Assumes t represents a post order representation of the tree as discussed
 // in class
//nonLeaf is the char value of the data in the non-leaf nodes
 //in classs we used (char) 128 for the non-leaf value
//scanner that grabs every single piece and converts it intoa huffman tree
		Stack<HuffmanTree> stack = new Stack<HuffmanTree>();
		for(int i = 0; i < t.length()-1; i++) {
			char c = t.charAt(i);
			if(c == nonLeaf) {
				HuffmanTree r = stack.pop();
				HuffmanTree l = stack.pop();
				stack.push(new HuffmanTree(l,r,c));
			}
			else {stack.push(new HuffmanTree(c));}
		}
		while(!stack.isEmpty()) {
			HuffmanTree r = stack.pop();
			if(!stack.isEmpty()) {
				HuffmanTree l = stack.pop();
				stack.push(new HuffmanTree(l,r,nonLeaf));
			}
			else {
				stack.push(r);
				break;
			}
		}
		root = stack.pop().root;
		current = root;
		
	}
	public HuffmanTree(HuffmanTree b1, HuffmanTree b2, char d) {
//makes a new tree where b1 is the left subtree and b2 is the right subtree
//d is the data in the root
		root = new Node(b1.root,d,b2.root,null);
		b1.root.parent = root;
		b2.root.parent = root;
	}
 //use the move methods to traverse the tree
//the move methods change the value of current
 //use these in the decoding process
	public void moveToRoot() {
 //change current to reference the root of the tree
		current = root;
	}
	public void moveToLeft() {
 //PRE: the current node is not a leaf
 //change current to reference the left child of the current node
		current = current.left;
	}
	public void moveToRight() {
 //PRE: the current node is not a leaf
 //change current to reference the right child of the current node
		current = current.right;
	}

	public void moveToParent() {
 //PRE: the current node is not the root
 //change current to reference the parent of the current node
		current = current.parent;
	}
	public boolean atRoot() {
 //returns true if the current node is the root otherwise returns false
		return current == root;

	}
	public boolean atLeaf() {
//returns true if current references a leaf other wise returns false
		return ((current.left == null) && (current.right == null));


	}
	public char current() {
//returns the data value in the node referenced by current
		return current.data;

	}
	public class PathIterator implements Iterator<String> {
 //the iterator returns the path (a series of 0s and 1s) to each leaf
 //DO NOT compute all paths in the constructor
 //only compute them as needed (similar to what you did in homework 2)
 //add private methods and variables as needed
		private String previous;
		private Node prev;
		private Node next;
		private String path;
		
		public PathIterator() {
			Node cur = root;
			path = "";
			while(cur.left != null) {
				cur = cur.left;
				path += "0";
			}
			next = cur;
			
		}
		
		public boolean hasNext() {
			return next != null;
		}
		public String next() {
 //the format of the string should be leaf value, a space, a sequence of
 //0s and 1s
 //the 0s and 1s indicate the path from the root the node containing
 //the leaf value
			if(!hasNext()) {
				return "";
			}
			prev = next;
			previous = path;
			Node cur = prev.parent;
			path =  path.substring(0, path.length()-1);
			Node pre = prev;
			while(cur.right == pre) {
				pre = cur;
				cur = cur.parent;
				if(cur != null) path =  path.substring(0, path.length()-1);
				if(cur == null) {
					next = null;
					return prev.data + " " + previous;
				}
			}
			pre = cur;
			cur = cur.right;
			path += "1";
			while(cur.left != null) {
				pre = cur;
				cur = cur.left;
				path += "0";
				
			}
			while(cur.right != null) {
				pre = cur;
				cur = cur.right;
				path += "1";
			}
			next = cur;
			return prev.data + " " + previous;

		}
		
		public void remove() {
 //optional method not implemented
		}
	}
	public Iterator<String> iterator() {
//return a new path iterator object
		PathIterator r = new PathIterator();
		return r;

	}
	public String toString() {
//returns a string representation of the tree using the postorder format
 // discussed in class
		
		return toString(root);
	}
	
	private String toString(Node r) {	
		if(r == null) {return "";}
		return toString(r.left) + "" + toString(r.right) + "" + r.data;
	}
}


 