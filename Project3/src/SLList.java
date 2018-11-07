public class SLList<T> {
	private static class Node<T>{
		private T element;
		private Node<T> next;
		//*************************************************************************
		// CONSTRUCTOR
		//*************************************************************************
		public Node(T e, Node<T> n) {
			element = e;
			next = n;
		}
		//*************************************************************************
		// getElement()
		// return the value of an element
		//
		//*************************************************************************
		public T getElement() {
			return element;
		}
		//*************************************************************************
		// getNext()
		// return the next node
		//*************************************************************************
		public Node<T> getNext(){
			return next;
		}
		//*************************************************************************
		// setNext()
		// set the next node to a value
		public void setNext(Node<T> n) {
			next = n;
		}
	}
	
		//List Implementation
		private Node<T> head = null;
		private Node<T> tail = null;
		private int size = 0;
		// empty SLList
		public SLList() {
		}
		//*************************************************************************
		// size()
		// return size
		//*************************************************************************

		public int size() {
			return size;
		}
		//*************************************************************************
		// isEmpty()
		// check if the list is empty
		//*************************************************************************

		public boolean isEmpty() {
			return size == 0;
		}
		
		//*************************************************************************
		// first()
		// return the value of the first node
		//*************************************************************************

		public T first() {
			if(isEmpty()) {
				return null;
			}
			return head.getElement();
		}
		//*************************************************************************
		// last()
		// return the value of the last node
		//*************************************************************************

		public T last() {
			if(isEmpty()) {
				return null;
			}
			return tail.getElement();
		}
		//*************************************************************************
		// addFirst()
		// add a new node to the front of the list
		//*************************************************************************

		public void addFirst(T e) {
			head = new Node<>(e, head);
			if(isEmpty()) {
				tail = head;
			}
			size++;
		}
		//*************************************************************************
		// addLast()
		// add a new node to the back of the list
		//*************************************************************************

		public void addLast(T e) {
			Node<T> newNode = new Node<>(e, null);
			if(isEmpty()) {
				head = newNode;
			}
			else {
				tail.setNext(newNode);
			}
			tail = newNode;
			size++;
		}
		//*************************************************************************
		// removeFirst()
		// remove the first node and return its value 
		//*************************************************************************
		public T removeFirst() {
			if(isEmpty()) {
				return null;
			}
			T answer = head.getElement();
			head = head.getNext();
			size--;
			if(size == 0) {
				tail = null;
			}
			return answer;
		}
}
