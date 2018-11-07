package csc130;
/**
 * Title: EmptyListException class
 * Description: Class to check if the list is empty
 * Kevin Perez
 * 
 */
public class EmptyListException extends Exception {
	
	public EmptyListException (){
		System.out.println("Empty");
	}
	
	public EmptyListException (String msg){
		System.out.println(msg);
	}
}
