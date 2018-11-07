//****************************************************************************
//
// Hoang Phan
// Data Structures
// Programming Project #3: Binary Expression Trees
// November 6, 2018
// Instructor: Dr. Michael Scherger
//
//****************************************************************************
public class Stack<E> {
	
	// new single linked list
	private SLList<E> list = new SLList<>();
	// initialize an empty stack
	public Stack() {}
	
	//*************************************************************************
	// size()
	// this function is to return the size of the list
	// return value: int
	//*************************************************************************
	public int size() {
		return list.size();
	}
	
	//*************************************************************************
	// isEmpty()
	// this function is to check if the list if empty or not
	// return true if empty. Otherwise, false.
	//*************************************************************************
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	//*************************************************************************
	// push()
	// this function is to add element to the list
	// void: no return value
	//
	// parameters:
	// -----------------------------
	// element		  E
	//*************************************************************************
	public void push(E element) {
		list.addFirst(element);
	}
	
	//*************************************************************************
	// pop()
	// this function is to remove the first element and return the value of that element
	// return value: E
	//
	//*************************************************************************
	public E pop() {
		return list.removeFirst();
	}
	
	//*************************************************************************
	// top()
	// this function is to return the value of that element without removing it
	// return value: E
	//
	//*************************************************************************
	public E top() {
		return list.first();
	}
}
