package javaActivity3_1;

import java.util.*;

public class activity3_3b {
    public static void main(String[] args) {
        Deque<String> dq = new LinkedList<String>();

        //Add elements to the deque
        dq.add("Tiger");
        dq.addFirst("Lion");
        dq.addLast("Wolf");
        
        //Alternate way to add elements to queues
        dq.offer("Hyena");
        dq.offerFirst("Puma");
        dq.offerLast("Panther");

        //Iterate through the queue elements.
        Iterator<String> iterator = dq.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        //Peek at head element
        System.out.println("Peek: " + dq.peekFirst());
        System.out.println("Peek: " + dq.peekLast());
        //To show that the element have not been deleted
        System.out.println("After peek: " + dq);
        
        //Search for element
        System.out.println("Contains Wolf?: " + dq.contains("Wolf"));

        //Remove the first and last element
        dq.removeFirst();
        dq.removeLast();
        System.out.println("dq after removing first and last elements: " + dq);
        System.out.println("Size of deque after removal: " + dq.size());
    }
}
