package javaActivity4_1;

import java.util.*;

	public class activity4_1 {

	    public static void main(String[] args) {
	        Scanner scan = new Scanner(System.in);
	        ArrayList<Integer> list = new ArrayList<Integer>();
	        Random indexGen = new Random();
	        
	        System.out.print("Enter integers please ");
	        System.out.println("(EOF or non-integer to terminate): ");

	        while(scan.hasNextInt()) {
	            list.add(scan.nextInt());
	        }

	        Integer nums[] = list.toArray(new Integer[0]);
	        int index = indexGen.nextInt(nums.length);
	        System.out.println("Index value generated: " + index);
	        System.out.println("Value in arary at generated index: " + nums[index]);

	        scan.close();
	    }
	}