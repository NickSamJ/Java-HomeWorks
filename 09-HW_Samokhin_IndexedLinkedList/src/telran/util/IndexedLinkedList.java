package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

public class IndexedLinkedList<T> implements IndexedList<T>{

	private static class Node<T>{
		public T obj;
		public Node<T> next;
		public Node<T> prev;
		public Node(T obj) {
			this.obj = obj;
		}
	}
	
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	public IndexedLinkedList(){
		
	}
	public IndexedLinkedList(int dummy){
		
	}
	
	@Override
	public void add(T obj) {
		Node<T> newNode = new Node<>(obj);
		if(head == null) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		size++;
	}

	// Homework
	@Override
	public boolean add(int index, T obj) {
		if (isValidIndex(index)) {
			Node<T> newNode = new Node<>(obj);
			
				Node<T> nextNode = find(index);
				
				if (index==0) {
					head.prev = newNode;
					head = newNode;
				}
				nextNode.prev.next = newNode;
				newNode.next = nextNode;					
				newNode.prev = nextNode.prev;
				nextNode.prev = newNode;	

			size++;
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public int binarySearch(T pattern) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int binarySearch(T pattern, Comparator<T> comp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IndexedList<T> filter(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T get(int ind) {
		T res = null;
		if(isValidIndex( ind)) {
			res = ind<size/2 ? getFromLeft(ind).obj : getFromRight(ind).obj;
		}
		return res;
//		return null;
	}
	
//	private T getFromRight(int ind) {
//		Node<T> current = tail;
//		for (int i = size-1; i > ind; i--) {
//			current = current.prev;
//		}
//		return current.obj;
//	}
//
//	private T getFromLeft(int ind) {
//		Node<T> current = head;
//		for (int i = 0; i < ind; i++) {
//			current = current.next;
//		}
//		return current.obj;
//	}
	private Node<T> getFromRight(int ind) {
		Node<T> current = tail;
		for (int i = size-1; i > ind; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getFromLeft(int ind) {
		Node<T> current = head;
		for (int i = 0; i < ind; i++) {
			current = current.next;
		}
		return current;
	}
	private Node<T> find(int index){
		return index<size/2 ? getFromLeft(index) : getFromRight(index); 
	}
	private boolean isValidIndex(int ind) {
		return ind >= 0 && ind <size;
	}

	@Override
	public int indexOf(Object pattern) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object pattern) {
		// TODO Auto-generated method stub
		return 0;
	}

	// Homework
	@Override
	public T remove(int index) {
		T result = null;
		if(isValidIndex(index)) {
			result = this.get(index);
			Node<T> toRemove = find(index);
			if(index == 0) {
				head = toRemove.next;
				head.prev = head;
			}else if (index==size-1) {
				tail = toRemove.prev;
				tail.next = tail;
			}else {
				toRemove.next.prev = toRemove.prev;
				toRemove.prev.next = toRemove.next;
			}

			toRemove = null;
			size--;
		}
		return result;
	}

	@Override
	public Object remove(Object index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object set(int index, T newObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void sort() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sort(Comparator<T> comparator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
