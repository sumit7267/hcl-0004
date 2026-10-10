package OOPS;

import java.util.*;

public class StackDemo {
    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();

        st.push(10);
        st.push(20);
        st.push(30);
        System.out.println(st);                  // [10, 20, 30]  (left = bottom, right = top)

        System.out.println(st.peek());           // 30  (top dekha)
        System.out.println(st.pop());            // 30  (top nikala)
        System.out.println(st);                  // [10, 20]

        System.out.println(st.size());           // 2
        System.out.println(st.isEmpty());        // false
        System.out.println(st.contains(10));     // true
        System.out.println(st.search(10));       // 2   (top se 2nd position, 1-based)
        System.out.println(st.search(99));       // -1  (nahi mila)

        st.clear();
        System.out.println(st.isEmpty());        // true
        // st.pop();  -> EmptyStackException (khali stack pe pop)
    }
}
