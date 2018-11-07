import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//****************************************************************************
//
// Hoang Phan
// Data Structures
// Programming Project #3: Binary Expression Trees
// November 6, 2018
// Instructor: Dr. Michael Scherger
//
//****************************************************************************
public class BTree {
	private Stack<BTNode> myStack = new Stack<BTNode>();
	private class BTNode{
		BTNode left, right;
		String data;

		public BTNode() {
			left = null;
			right = null;
		}
		public BTNode(String c) {
			left = null;
			right = null;
			data = c;
		}
		public void setLeft(BTNode n) {
			left = n;
		}
		public void setRight(BTNode n) {
			right = n;
		}
		
		public BTNode getRight() {
			return right;
		}
			
	}
	
	private BTNode root = new BTNode();
	
	// constructor of an empty BTree()
	public BTree(){
	}
	
	//**************************************************************************
	// isEmpty()
	// This function is to check if a node is empty
	// return true if empty. Otherwise, false.
	//**************************************************************************
	public boolean isEmpty() {
		return root == null;
	}
	
	//**************************************************************************
	// OPERANDS
	// This function is to check if the token is an operand.
	// If yes, return true. Otherwise, return false.
	// 
	// boolean: return true or false
	// -----------------------------
	//
	// local variables:
	// -----------------------------
	// s         String
	//**************************************************************************
	public static boolean operands(String s) {
		if(s.matches(".*[a-z, A-Z].*"))
		{
			return true;
		}
			return false;
	}
	
	//**************************************************************************
	// OPERATORS
	// This function is to check if the token is one of these operators '+', '-', '*', '/', '^'.
	// If yes, return true. Otherwise, return false.
	// 
	// boolean: return true or false
	// -----------------------------
	//
	// local variables:
	// -----------------------------
	// s         String
	//**************************************************************************
	public static boolean operators(String s) {
		if(s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/")||s.equals("^")) {
			return true;
		}
		return false;
	}
	
	//**************************************************************************
	// insert()
	// This function is to insert data into root
	// void: no return value
	//
	//**************************************************************************
	public void insert(String data) {
		root = addNode(root, data);
	}
	
	//**************************************************************************
	// addNode()
	// This function is used to add a new node to the tree
	// return value: BTNode
	// -------------------------------
	// 
	// parameters:
	// -------------------------------
	// node		BTNode
	// data 		String
	//
	//**************************************************************************
	public BTNode addNode(BTNode node, String data) {
		if(node == null) {
			node = new BTNode(data);
		}
		else {
			if(node.getRight() == null)
				node.right = addNode(node.right, data);
			else
				node.left = addNode(node.left, data);
		}
		return node;
	}
	
	//**************************************************************************
	// height()
	// This function performs returning the value of level of the tree
	// return value: int
	// --------------------------------
	//
	// parameters:
	// --------------------------------
	// root		BTNode
	//**************************************************************************
	public int height(BTNode root) {
		if(root == null)
			return 0;
		else {
			int left = height(root.left);
			int right = height(root.right);
			
			if(left > right)
				return(left+1);
			else
				return (right +1);
		}
	}
	
	//**************************************************************************
	// levelOrderNotation()
	// This function is to print out all the nodes' data of each level
	// void: no return value
	//
	// parameters:
	// -------------------------------
	// root 		BTNode
	// level		int
	//**************************************************************************
	public void levelOrderNotation(BTNode root, int level) {
		if(root == null)
			return;
		if(level == 1)
			System.out.print(root.data + " ");
		else if(level > 1) {
			levelOrderNotation(root.left, level -1);
			levelOrderNotation(root.right, level -1);
		}
	}
	
	//**************************************************************************
	// levelOrder()
	// This function is to print out all the nodes' data of all level in the level order traversal
	// void: no return value
	//
	// local variables:
	// --------------------------------
	// h 		int
	//**************************************************************************
	public void levelOrder() {
		int h = height(root);
		System.out.print("Level Order Notation: ");
		for(int i = 0; i <= h; i++) {
			levelOrderNotation(root, i);
		}
		System.out.println();
	}	
	
	//**************************************************************************
	// postToTree()
	// This function is to build up the tree by using postfix expression
	// void: no return value
	//
	// parameters:
	// -------------------------------
	// postfix		String
	//
	// local variables:
	// st		StringTokenizer		
	// token		String
	// op		BTNode
	//**************************************************************************
	public void postToTree(String postfix) {
		StringTokenizer st = new StringTokenizer(postfix, " ");
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			BTNode op = new BTNode(token);
			if(operands(token)) {
				myStack.push(op);
			}
			else if(operators(token)) {
				op.setRight(myStack.pop());
				op.setLeft(myStack.pop());
				myStack.push(op);
			}
		}
		root = myStack.pop();
	}
	
	//**************************************************************************
	// nullElement()
	// This function is to check if all the nodes in the list is not null
	// boolean: if not null, return false. Otherwise, return true
	// 
	// parameters:
	// --------------------------
	// list		List<BTNode>
	//**************************************************************************
	public boolean nullElement(List<BTNode> list) {
		for(BTNode object : list) {
			if(object != null) 
				return false;
		}
		return true;
	}
	
	//**************************************************************************
	// printTree()
	// This function is to print the whole tree starting from the root
	// void: no return value
	//
	// parameters:
	// ------------------------------
	// root		BTNode
	//
	// local variables:
	// ------------------------------
	// maxHeight		int
	// list			List<BTNode>
	//**************************************************************************
	public void printTree(BTNode root) {
		int maxHeight = height(root);
		List<BTNode> list = new ArrayList<BTNode>();
		list.add(root);
		printNodes(list, 1, maxHeight); //1 is for the 1st level and maxHeight is the
										// the last level
	}
	
	//**************************************************************************
	// printNodes()
	// This function is to print all the nodes and a proper number of spaces of each level
	// void: no return value
	//
	// parameters:
	// -------------------------------
	// nodes		List<BTNode>
	// height	int
	// max		int
	//
	// local variables:
	// floor				int
	// edge				int
	// firstSpaces		int
	// betweenSpaces		int
	// newNodes			List<BTNode>
	//**************************************************************************
	public void printNodes(List<BTNode> nodes, int height, int max) {
		if(nodes.isEmpty() || nullElement(nodes)) {
			return;
		}
		int floor = max - height; // if max = 5, then floor = 4
		int lines = (int) Math.pow(2, Math.max(floor - 1,0)); // lines = 2^3 = 8
		int firstSpaces = (int) Math.pow(2, floor) -1; // firstSpaces = 2^4 - 1 = 15
		int betweenSpaces = (int) Math.pow(2, floor + 1) - 1; // betweenSpaces = 2^5 - 1 = 31
		
		printSpaces(firstSpaces);
		List<BTNode> newNodes = new ArrayList<BTNode>();
		for(BTNode node : nodes) { 
			if(node != null) {
				System.out.print(node.data);
				newNodes.add(node.left);
				newNodes.add(node.right);
			}
			else {
				newNodes.add(null);
				newNodes.add(null);
				System.out.print(" ");
			}
			printSpaces(betweenSpaces);
		}
		System.out.println("");
		for (int i = 1; i <= lines; i++) {
	         for (int j = 0; j < nodes.size(); j++) {
	            printSpaces(firstSpaces - i);
	            if (nodes.get(j) == null) {
	               printSpaces(lines + lines + i + 1);
	               continue;
	            }
	   
	            if (nodes.get(j).left != null)
	               System.out.print("/");
	            else
	               printSpaces(1);
	   
	            printSpaces(i + i - 1);
	   
	            if (nodes.get(j).right != null)
	               System.out.print("\\");
	            else
	               printSpaces(1);
	   
	            printSpaces(lines + lines - i);
	         }
	         System.out.println("");
		}
	   printNodes(newNodes, height + 1, max);
	}
	
	//**************************************************************************
	// printSpaces()
	// This function is to print a proper number of spaces according to each level
	// void: no return value
	//
	// parameters:
	// ------------------------------
	// count		int
	//**************************************************************************
	public void printSpaces(int count) {
		for(int i = 0; i < count; i++) {
			System.out.print(" ");
		}
	}
	
	//**************************************************************************
	// expTree()
	// This function is to print the whole tree. It is used for the main function in
	// Project3.java without having any parameters
	// void: no return value
	//**************************************************************************
	public void expTree() {
		printTree(root);
	}
}
