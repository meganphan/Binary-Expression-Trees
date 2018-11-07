//****************************************************************************
//
// Hoang Phan
// Data Structures
// Programming Project #3: Binary Expression Trees
// November 6, 2018
// Instructor: Dr. Michael Scherger
//
//****************************************************************************
public class Queue<E> {
	//create new single linked list
	private SLList<E> list = new SLList<>();
	//initialize an empty queue
	public Queue() {}
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
	// enqueue()
	// this function is to add element to the list
	// void: no return value
	//
	// parameters:
	// -----------------------------
	// e		  E
	//*************************************************************************
	public void enqueue(E e) {
		list.addLast(e);
	}
	//*************************************************************************
	// dequeue()
	// this function is to remove the first element and return the value of that element
	//
	//*************************************************************************
	public E dequeue() {
		return list.removeFirst();
	}
	

}
