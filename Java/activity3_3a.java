package javaActivity3_1;

import java.util.LinkedList;
import java.util.Queue;

public class activity3_3a {
    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();

        //Add elements
        for (int i=0;i<5;i++) {
            q.add(i);
        }

        //Display contents of the queue. 
        System.out.println("Elements in queue: " + q);

        //To remove the head of queue.
        int removeEle = q.remove();
        System.out.println("removed element: " + removeEle);

        //To view the head of queue
        int headEle = q.peek();
        System.out.println("head of queue: " + headEle);

        int size = q.size();
        System.out.println("Size of queue: " + size);
    }
}
