package OOPS;

import java.util.*;

public class QueueDemo {
    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();

        q.offer(10);                      // enqueue
        q.offer(20);
        q.offer(30);
        System.out.println(q);            // [10, 20, 30]

        System.out.println(q.peek());     // 10  (front, nikalta nahi)
        System.out.println(q.poll());     // 10  (front nikala)
        System.out.println(q);            // [20, 30]
        System.out.println(q.size());     // 2
        System.out.println(q.isEmpty());  // false
    }
}
