package csc130;

/**
 * Driver
 * Kevin Perez]
 * CSC130
 */

import java.util.Iterator;

public class Driver {
	static int moves = 0;
	private static final char[] table = 
		 {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	
	
	public static void main(String[] args) {
		System.out.println(sum(1,7));
		System.out.println(pow(3,4));
		System.out.println(base(22235,16));
		hanoi(36);
		DoublyLinkedList <Integer> list = new DoublyLinkedList<Integer>();
		list.addToRear(1);
		list.addToRear(2);
		list.addToRear(3);
		list.addToRear(4);
		list.addToRear(5);
		list.addToRear(6);
		reverse(list);
		

	}//1
	public static int sum(int a, int b) {
		if (a == b)
			return a;
		else if (a - a == 1)
			return b + a;
		
		return sum(a,(b + b)/2) + sum (((a + b)/2)+1,b);
	}
	//2
	public static void hanoi(int discs){ 
		 for (int i = 1; i < (1 << discs); i++) { 
		 int from = (i & i - 1) % 3; 
		 int to = ((i | i - 1) + 1) % 3;
		 moves++;
		 System.out.println(i + " Move " + from + " to " + to); 
		 } 
		 System.out.println(moves);
		}
	//value over 30 crashes
	//3
	public static int pow (int x, int y){
		if (y == 0)
			return 1;// if y=0 output 1
		else if (y>0 && y%2 == 1)//y is greater than 0 and y is odd
			return (pow(x,(y-1)/2))*(pow(x,(y-1)/2))*x;
		else if (y>0 && y%2 == 0)//y>0 and y is even
			return pow(x,y/2)*pow(x,y/2);
		return -1;
	}
	//4
	public static void reverse (DoublyLinkedList <Integer> list){
		if (list.isEmpty())//Empty list
			return;
		int i=0;
		try {
			i = list.removeFirst();
			reverse(list);
			System.out.println(i);
		} catch (EmptyListException e) {
			System.out.println(e);;
		}
		
	}

	//5
	public static String base (int x, int y){
		
		
		if (x/y == 0)
			return  "" + table[x%y];
		
		
		return base (x/y,y) + "" + table[x%y];
			
	}

}

