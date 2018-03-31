/*	Noah Huerta
 * cssc0929
 */

package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
//import java.util.ConcurrentModificationException;

public class ArrayLinearList<E> implements LinearListADT<E> {
	public static final int DEFAULT_MAX_CAPACITY = 100;
	private E[] array;
	private int currentSize;
	private E storage;
	public int maxSize;
	
	public ArrayLinearList(){
		array =(E[]) new Object[DEFAULT_MAX_CAPACITY];
		currentSize = 0;
		maxSize = DEFAULT_MAX_CAPACITY;
	}

 
    public void addLast(E obj) {
    	insert(obj,currentSize+1);
    	}
    

    public void addFirst(E obj) {
    	insert(obj,1);
    }   
    
    public void insert(E obj, int location) {    	
    	if (location > size()+1 || location<=0 )
    		throw new RuntimeException("Invalid Array Position");
    	else{
    		if (currentSize==maxSize)
        		growArray();
    		
    		location--;
    		for (int i = currentSize;i>location;i--){
    			array[i]=array[i-1];
    		}
    		array[location]=obj;
    		currentSize++;
    	}
    }   

    public E remove(int location) {
    	if (location >currentSize || location<0)
    		throw new RuntimeException("Invalid Array Location");
    	else{
    		location--;
    		storage = array[location];
    		
    		for (int i = location;i<currentSize-1;i++){
    			array[i]=array[i+1];
    		}
    		currentSize--;
    		if (currentSize <= maxSize/4)
    			shrinkArray();
    		return storage;
    	}
    		
    }
    
    public E remove(E obj) {
    	for (int i = 0; i<currentSize;i++){
    		if (((Comparable<E>)obj).compareTo(array[i])==0){
    			return remove(i+1);
    		}
    	}
    	return null;
    }
    
    public E removeFirst() {
    	if (isEmpty())
    	return null;
    	else{
    		storage = array[0];
    		
    		for (int i = 0;i<currentSize-1;i++){
    			array[i]=array[i+1];
    		}
    		currentSize--;
    		
    		if (currentSize <= maxSize/4)
    			shrinkArray();
    		
    		return storage;
    	}
    }   
    
    public E removeLast() {
    	if(!isEmpty()){
    		storage = array[currentSize-1];
    	currentSize--;
    	
    	if (currentSize <= maxSize/4)
			shrinkArray();
    	
    	return storage;
    	}
    	else{
    		return null;
    	}
    }
    	         

    public E get(int location)  {    	
    	if(location>currentSize || location<0)
    		throw new RuntimeException("Invalid Array Location");
    	else{
    	location--;
    	return array[location];
    		}
    	}    


    public boolean contains(E obj) {
    	 if (locate(obj)>=1)
    		return true;
    	else
    		return false;    	
    	} 
    

    public int locate(E obj) {
    	for (int i = 0; i<currentSize;i++){
    		if (((Comparable<E>)obj).compareTo(array[i])==0){
    			return i+1;
    		}
    	}
    	return -1;
    }       


    public void clear() {currentSize=0;}

    public boolean isEmpty() {
    	if(currentSize == 0)
    		return true;
    	else
    		return false;
    }

    public int size() {
    	return currentSize;
    	}
    

    public Iterator<E> iterator() {
    	return new IteratorHelper();
    }
    	
    	class IteratorHelper<E> implements Iterator<E>{
    		int iterIndex;
    		long stateCheck;
    		long modificationCounter = 0;
    		
    		public IteratorHelper(){
    			iterIndex = 0;
    			stateCheck = modificationCounter;
    		}
    		
    		public boolean hasNext(){
    			if(stateCheck != modificationCounter){
    				//throw new ConcurrentModificationException();
    			}
    				return iterIndex < currentSize;    			
    		}
    		public E next(){
    			if (!hasNext()){
    				throw new NoSuchElementException();
    			}
    				return (E) array[iterIndex++];
    			
    		}
    		public void remove(){
    			throw new UnsupportedOperationException();
    		}
    	}
    	
    public void growArray(){
    	maxSize *=2;
    	E[] temp = (E[]) new Object[maxSize];
    	for (int i = 0; i<currentSize;i++){
    		temp[i] = array[i];
    	}
    	array = temp;
    }
    
    public void shrinkArray(){
    	maxSize /=2;
    	E[] temp = (E[]) new Object[maxSize];
    	for (int i = 0; i<currentSize;i++){
    		temp[i] = array[i];
    	}
    	array=temp;
    }
    
 }

