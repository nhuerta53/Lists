/*	Noah Huerta
 * cssc0929
 */

package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class LinearLinkedList<E> implements LinearListADT<E>{
	
	private class Node<T>{
		T data;
		Node<T> next;
		
		public Node(T d){
			data = d;
			next = null;
		}
		
	}
	
	public Node<E> head, tail;
	private int currentSize;
	
	public LinearLinkedList(){
		head = tail = null;
		currentSize = 0;
	}

    public void addLast(E obj) {
    	Node<E> newNode = new Node<E>(obj);
    	if(isEmpty())
    		head=tail=newNode;
    	else{
    	tail.next = newNode;
    	tail = newNode;
    	}
    	currentSize++;
    	
    }
 
    public void addFirst(E obj) {
    	Node<E> newNode = new Node<E>(obj);
    	if(isEmpty())
    		head=tail=newNode;
    	else{
    	newNode.next = head;
    	head = newNode;
    		}
    	currentSize++;
    }    

    public void insert(E obj, int location) {
    	if (location >currentSize+1 || location<0)
    		throw new RuntimeException("Invalid List Location");
    	else if (location == currentSize+1)
    		addLast(obj);
    	else if(location==1)
    	addFirst(obj);
    	else{
    	Node<E> newNode = new Node<E>(obj);
    	Node<E> tmp = head;
    	Node<E> tmp2 = head.next;
    	for (int i =1;i<location-1;i++){
    		tmp = tmp.next;
    		tmp2 = tmp2.next;
    	}
    	tmp.next = newNode;
    	newNode.next = tmp2;		
    	
    	currentSize++;
    	}
    }    

    public E remove(int location) {
    	if (isEmpty())
    		return null;
    	if (location >currentSize+1 || location<0)
    		throw new RuntimeException("Invalid List Location");
    	else{
    	E storage;
    	Node<E> tmp = head;
    	Node<E> tmp2 = head.next;
    	if (location == 1){
    		storage = head.data;
    		head = head.next;
    	}
    	else{
    		
    	for (int i =1;i<location-1;i++){
    		tmp = tmp.next;
    		tmp2 = tmp2.next;
    	}
    		storage = tmp.next.data;
    		tmp.next = tmp2.next;
    		}
    	currentSize--;
    	return storage;
    	}
    }
 
    public E remove(E obj) {
    	Node<E> tmp = head;
    	for(int i = 1; i<currentSize+1;i++){
    	if (((Comparable<E>)obj).compareTo(tmp.data)==0)
    		return remove(i);
    		tmp=tmp.next;
    	}
    	return null;
    }
    
    public E removeFirst() {
    	return remove(1); 
    }   

    public E removeLast() {
    	return remove(currentSize);
    }              

    public E get(int location) {
    	if (location >currentSize+1 || location<0)
    		throw new RuntimeException("Invalid List Location");
    	else{
    	Node<E> tmp = head;
    	E storage;
    	for (int i=1;i<location;i++)
    		tmp=tmp.next;
    	
    	storage = tmp.data;
    	return storage;
    	}
    }       

    public boolean contains(E obj) {
    	Node<E> tmp = head;
    	for(int i = 1; i<currentSize+1;i++){
        	if (((Comparable<E>)obj).compareTo(tmp.data)==0)
        		return true;
        	
        		tmp=tmp.next;
        }
    	return false;
    }   

    public int locate(E obj) {
    	Node<E> tmp = head;
    	for(int i = 1; i<currentSize+1;i++){
        	if (((Comparable<E>)obj).compareTo(tmp.data)==0)
        		return i;
        		tmp=tmp.next;
        }
    	return -1;
    }        

    public void clear() {
    	head = tail = null;
    	currentSize=0;
    }

    public boolean isEmpty() {
    	if (currentSize == 0)
    	return true;
    	
    	return false;
    } 

    public int size() {
    	return currentSize;
    } 

    public Iterator<E> iterator() {
    	return new Iterator(){
    
    	
    	
    		int iterIndex;
    		E tmp;
    		Node<E> tmpHead = head;
    		long stateCheck;
    		long modificationCounter = 0;
    		
    		public void Iterator(){
    			tmp =  (E) head;
    			iterIndex=0;
    			stateCheck = modificationCounter;
    		}
    		
    		public boolean hasNext(){
    			if(stateCheck != modificationCounter){
    				throw new ConcurrentModificationException();
    			}
    				return tmpHead !=null;    			
    		}
    		public E next(){
    			if (!hasNext()){
    				throw new NoSuchElementException();
    			}
    				tmp = tmpHead.data;
    				tmpHead = tmpHead.next;
    				iterIndex++;
    				return (E) tmp;
    			
    		}
    		public void remove(){
    			throw new UnsupportedOperationException();
    		}
    	};
    }
    
    
}
