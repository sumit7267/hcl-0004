package OOPS;

import java.util.*;

public class StackDemo {
    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();

        st.push(10);
        st.push(20);
        st.push(30);
        System.out.println(st);                  

        System.out.println(st.peek());
        System.out.println(st.pop());
        System.out.println(st);

        System.out.println(st.size());
        System.out.println(st.isEmpty());
        System.out.println(st.contains(10));
        System.out.println(st.search(10));
        System.out.println(st.search(99));

        st.clear();
        System.out.println(st.isEmpty());

    }
}
