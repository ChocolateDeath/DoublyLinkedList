package csc130;
/**
 *Doubly Linked List
* Kevin Perez
 * 
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T extends Comparable<T>> implements Iterable<T> {
	protected class Node<E> {
		E data;
		Node<E> previous;
		Node<E> next;

		Node() {
			data = null;
			previous = null;
			next = null;
		}

		Node(E d) {
			data = d;
			previous = null;
			next = null;
		}

		Node(E d, Node<E> p, Node<E> n) {
			data = d;
			previous = p;
			next = n;
		}
	}

	public class ListIterator<E> implements Iterator<E> {

		private Node<E> list;
		private int count, current;

		public ListIterator(Node<E> head, int c) {
			list = head;
			count = c;
			current = 0;
		}

		public boolean hasNext() {
			return current < count;
		}

		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			E data = list.data;
			list = list.next;
			current++;
			return data;
		}

		public void remove() {
			// does nothing
		}
	}

	private Node<T> head, tail;
	private int size;

	public DoublyLinkedList() {

	}

	public void clear() {
		head = null;
		tail = null;
		System.gc();
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public T first() throws EmptyListException {
		if (size == 0)
			throw new EmptyListException();
		return head.data;
	}

	public T last() throws EmptyListException {
		if (size == 0)
			throw new EmptyListException();
		return tail.data;
	}

	public boolean contains(T d) {
		return search(d) != -1;
	}

	public Iterator<T> iterator() {
		return new ListIterator<T>(head, size);
	}

	public String toString() {
		String str = "";

		Iterator<T> iter = new ListIterator<T>(head, size);
		while (iter.hasNext())
			str += iter.next() + (iter.hasNext() ? "<->" : "");

		return str;
	}

	public String reverse() {
		String str = "";

		Node<T> trav = tail;
		while (trav != null) {
			str += trav.data + (trav.previous != null ? "<->" : "");
			trav = trav.previous;
		}

		return str;
	}

	public void addToFront(T d) {
		if (head == null) {
			head = new Node<T>(d);
			tail = head;
		} else {
			head = new Node<T>(d, null, head);
			head.next.previous = head;
		}
		size++;
	}

	public void addToRear(T d) {
		if (tail == null) {
			head = new Node<T>(d);
			tail = head;
		} else {
			tail.next = new Node<T>(d, tail, null);
			tail = tail.next;
		}
		size++;
	}

	public int search(T d) {
		int pos = -1;
		boolean found = false;
		int i = 0;

		Node<T> trav = head;
		while (trav != null && !found) {
			if (trav.data.equals(d)) {
				found = true;
				pos = i;
			} else {
				i++;
				trav = trav.next;
			}
		}
		return pos;
	}

	public void addAtPosition(T d, int pos) throws NoSuchElementException {
		if (pos < 0 || pos > size)
			throw new NoSuchElementException();

		if (size == 0) {
			head = new Node<T>(d);
			tail = head;
		} else {
			if (pos == 0) {
				head = new Node<T>(d, null, head);
				head.next.previous = head;
			} else if (pos == size) {
				tail.next = new Node<T>(d, tail, tail.next);
				tail = tail.next;
			} else {
				Node<T> trav = head;

				for (int i = 1; i < pos; i++)
					trav = trav.next;

				Node<T> temp = new Node<T>(d, trav, trav.next);
				trav.next.previous = temp;
				trav.next = temp;
			}
		}
		size++;
	}

	public T removeFirst() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException();

		T temp = head.data;
		head = head.next;
		if (head != null)
			head.previous = null;
		else
			head = tail = null;
		size--;
		return temp;
	}

	public T removeLast() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException();

		T temp = tail.data;

		if (tail.previous == null) {
			tail = null;
			head = null;
		} else {
			tail = tail.previous;
			tail.next = null;
		}
		size--;
		return temp;
	}

	public T remove(T d) throws EmptyListException {
		T temp = null;
		boolean found = false;

		if (isEmpty())
			throw new EmptyListException();

		if (head.data.equals(d))
			removeFirst();
		else if (tail.data.equals(d))
			removeLast();
		else {
			Node<T> trav = head;
			while (trav != null && !found)
				if (trav.data.equals(d)) {
					temp = trav.data;
					trav.previous.next = trav.next;
					trav.next.previous = trav.previous;
					trav = null;
					found = true;
				} else
					trav = trav.next;
			if (found == true)
				size--;
			else
				throw new RuntimeException("Not found Exception");
		}
		return temp;
	}

	public T removeAtPosition(int pos) throws EmptyListException,
			NoSuchElementException {
		T temp = null;

		if (isEmpty())
			throw new EmptyListException();
		if (pos < 0 || pos > size)
			throw new NoSuchElementException();

		if (pos == 0)
			removeFirst();
		else if (pos == size - 1)
			removeLast();
		else {
			Node<T> trav = head.next;

			for (int i = 1; i < pos; i++)
				trav = trav.next;

			temp = trav.data;
			trav.previous.next = trav.next;
			trav.next.previous = trav.previous;
			trav = null;
			size--;
		}
		return temp;
	}
}