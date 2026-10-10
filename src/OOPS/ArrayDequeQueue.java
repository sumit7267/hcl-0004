package OOPS;

import java.util.*;

public class ArrayDequeQueue {
    public static void main(String[] args) {
        Queue<Integer> q = new ArrayDeque<>();

        q.offer(10);                      // enqueue (peeche se)
        q.offer(20);
        q.offer(30);
        System.out.println(q);            // [10, 20, 30]

        System.out.println(q.peek());     // 10  (front dekha, nikala nahi)
        System.out.println(q.poll());     // 10  (front nikala)
        System.out.println(q);            // [20, 30]

        System.out.println(q.size());     // 2
        System.out.println(q.isEmpty());  // false

        q.poll();
        q.poll();
        System.out.println(q.poll());     // null  (khali queue pe poll null deta hai)
    }
}
