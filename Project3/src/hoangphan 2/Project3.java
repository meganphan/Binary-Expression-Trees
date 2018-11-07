//****************************************************************************
//
// Hoang Phan
// Data Structures
// Programming Project #3: Binary Expression Trees
// November 6, 2018
// Instructor: Dr. Michael Scherger
//
//****************************************************************************

//IMPORTS
import java.util.Scanner;
import java.util.StringTokenizer;

public class Project3 {
	//*************************************************************************
	// global variables:
	// myQueue Queue<String>
	// myStack Stack<String>
	//*************************************************************************
	private static Queue<String> myQueue = new Queue<String>();
	private static Stack<String> myStack = new Stack<String>();
	private static BTree myTree = new BTree();
	
	//*************************************************************************
	// MAIN()
	// This method is the entry point of the program
	//
	// void: no return value
	// ------------------------
	// 
	// arguments:
	// ------------------------
	// args		String[]
	//
	// local variables:
	// ------------------------
	// scan    	Scanner
	//**************************************************************************
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()) {
			String first = scan.next(); // scan the first character of each line
			//PREFIX EXPRESSION
			if(first.equals("!")) {
				String prefix = scan.nextLine();
				System.out.println("Prefix Expression: "+prefix);
				String infix = preToIn(reverse(prefix)); // reverse the prefix expression before converting
				
				//**************************************************************************
				// The first conversion will go through the expression input and find out if the input is valid
				// or not. If the conversion is successful, then go on. Otherwise, the return value is null.
				//**************************************************************************
				if(infix != null) {
					String postfix = inToPost(infix);
					System.out.println("Infix Expression: "+infix);
					System.out.println("Postfix Expression: "+ postfix);
				
					myTree.postToTree(postfix);
					myTree.levelOrder();
					myTree.expTree();
					System.out.println();
				}
			}
			//INFIX EXPRESSION
			else if(first.equals("@")) {
				String infix = scan.nextLine();
				String fullParens = infixParenthesized(infix);
				System.out.println("Infix Expression: "+ fullParens);
				String postfix = inToPost(fullParens);
				if(postfix != null) {
					String prefix = postToPre(postfix);
					System.out.println("Prefix Expression: "+prefix);
					System.out.println("Postfix Expression: "+ postfix);
					
					myTree.postToTree(postfix);
					myTree.levelOrder();
					myTree.expTree();
					System.out.println();
				}
			}
			//POSTFIX EXPRESSION
			else if(first.equals("#")) {
				String postfix = scan.nextLine();
				System.out.println("Postfix Expression: "+postfix);
				String prefix = postToPre(postfix);
				if(prefix != null) {
					String infix = preToIn(reverse(prefix));
					System.out.println("Prefix Expression: "+prefix);
					System.out.println("Infix Expression: "+infix);
					
					myTree.postToTree(postfix);
					myTree.levelOrder();
					myTree.expTree();
					System.out.println();
				}
			}
			//ERROR MESSAGES IF THE FIRST CHARACTER IS NOT EITHER '!', '@', or '#'
			else {
				System.out.println("Expression: "+ first + " "+scan.nextLine());
				System.out.println("The first character should be either '!', '@', or '#'.");
				System.out.println();
				scan.nextLine();
			}
		}
		scan.close();
	}
	//**************************************************************************
	// OPERANDS
	// This function is to check if the token is a valid operand.
	// If yes, return true. Otherwise, return false.
	// 
	// boolean: return true or false
	// -----------------------------
	//
	// parameters:
	// -----------------------------
	// s					  String
	//
	// local variables:
	// -----------------------------
	// characters         String
	//**************************************************************************
	public static boolean operands(String s) {
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		// operands must be single characters
		if(characters.indexOf(s) != -1)
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
	// parameters:
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
	// REVERSE
	// This function is to reverse a string. This is used for prefix to infix conversion
	// 
	// return value: String
	// -----------------------------
	//
	// parameters:
	// -----------------------------
	// str		  String
	//
	// local variables:
	// -----------------------------
	// s          String
	// reverse	  String
	//**************************************************************************
	public static String reverse(String str) {
		String reverse = "";
        
        for(int i = str.length() - 1; i >= 0; i--)
        {
            reverse = reverse + str.charAt(i);
        }
        return reverse;
	}
	
	//**************************************************************************
	// PRETOIN
	// This function is to convert prefix expression to infix expression
	// return value: String
	// -----------------------------
	//
	// parameters:
	// -----------------------------
	// s	       		String
	//
	// local variables:
	// -----------------------------
	// st			StringTokenizer
	// a, b, op		String
	// infix			String
	// token			String
	//**************************************************************************
	public static String preToIn(String s) {
		StringTokenizer st = new StringTokenizer(s, " ");
		String a, b, op;
		String infix = "";
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			if(operands(token)) {
				myStack.push(token);
			}
			else if (operators(token)){
				a = myStack.pop();
				b = myStack.pop();
				op = "( "+ a + " "+token+" "+b+" )";
				myStack.push(op);
			}
			else {
				System.out.println("The expression may contain an invalid character.");
				System.out.println();
				return null;
			}
		}
		infix = myStack.pop();
		return infix;
	}
	
	//**************************************************************************
	// INTOPOST
	// This function is to convert infix expression to postfix expression
	// return value: String
	// -------------------------------
	//
	// parameters:
	// -----------------------------
	// s    		    String
	//
	// local variables:
	// -------------------------------
	// postfix 		String
	// st			StringTokenizer
	// sb			StringBuilder
	// token			String
	//**************************************************************************
	public static String inToPost(String s) {
		StringTokenizer st = new StringTokenizer(s, " ");
		String postfix;
		StringBuilder sb = new StringBuilder("");

		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			if( operators(token)) {
				myStack.push(token);
			}
			else if(token.equals("(")) {
				sb.append("");
			}
			else if(token.equals(")")) {
				sb.append(myStack.pop() + " ");
			}
			else if (operands(token)){
				sb.append(token + " ");
			}
			else {
				System.out.println("The expression may contain an invalid character.");
				System.out.println();
				return null;
				}
		}
		postfix = sb.toString();
		return postfix;
			
	}
	
	//**************************************************************************
	// POSTTOPRE
	// This function is to convert postfix expression to prefix expression
	// return value: String
	// -------------------------------
	//
	// parameters:
	// -----------------------------
	// s		        String
	//
	// local variables:
	// -------------------------------
	// prefix 		String
	// st			StringTokenizer
	// a, b, op		String
	// token			String
	//**************************************************************************
	public static String postToPre(String s) {
		String prefix = "";
		StringTokenizer st = new StringTokenizer(s, " ");
		String a,b,op;
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			if(operands(token)) {
				myStack.push(token);
			}
			else if (operators(token)){
				a = myStack.pop();
				b = myStack.pop();
				op = token+ " "+ b +" "+ a;
				myStack.push(op);
			}
			else {
				System.out.println("The expression may contain an invalid character.");
				System.out.println();
				return null;
				}
		}
		prefix = myStack.pop();
		return prefix;
	}
	
	public  static int precedence(String s) {
		if(s.equals("^"))
			return 3;
		if(s.equals("/") || s.equals("*"))
			return 2;
		if(s.equals("+") || s.equals("-"))
			return 1;
		return 0;
	}
	
	public static String infixParenthesized(String s) {
		String infix = "";
		String infix_1 = s + " $"; //add the end symbol to the infix expression
		StringTokenizer st = new StringTokenizer(infix_1, " "); 
		Stack<String> opStack = new Stack<String>(); // the stack that holds operands
		String a, b, op;
		while(st.hasMoreElements()) {
			String token = st.nextToken();
			if(operands(token)) {
				opStack.push(token);
			}
			if(operators(token)) {
				if(myStack.isEmpty()) {
					myStack.push(token);
				}
				else {
					if(precedence(myStack.top()) >= precedence(token)) {
			
						while(!myStack.isEmpty() && precedence(myStack.top()) >= precedence(token)) {
							b = opStack.pop();
							a = opStack.pop();
							op = "( "+ a + " "+ myStack.pop() + " "+ b + " )";
							opStack.push(op);
						}
						myStack.push(token);
					}
					else {
						myStack.push(token);
					}
				}
			}
			if(token.equals("(")) {
				myStack.push(token);
			}
			if(token.equals(")")) {
				if(opStack.size() >= 2) {
					b = opStack.pop();
					a = opStack.pop();

					op = "( "+ a +" "+ myStack.pop()+ " "+ b + " )";
					myStack.pop();
					opStack.push(op);
				}
			}
			if(token.equals("$")) {
				if(opStack.size() >= 2) {
					b = opStack.pop();
					a = opStack.pop();
					op = "( "+ a + " "+ myStack.pop() + " "+ b + " )";
					opStack.push(op);
				}
			}
		}
		if(opStack.size() >=2) {
			b = opStack.pop();
			a = opStack.pop();
			op = "( "+ a + " "+ myStack.pop() + " "+ b + " )";
			opStack.push(op);
		}
		
		if(opStack.isEmpty()) return null;
		infix = opStack.pop();
		return infix;
	}
}
