package OOPS;

import java.util.*;

public class HashMapDemo {
    public static void main(String[] args) {
        Map<String, Integer> marks = new HashMap<>();

        // 1) put: add / update
        marks.put("Amit", 80);
        marks.put("Riya", 92);
        marks.put("Karan", 65);
        marks.put("Amit", 85);                          // duplicate key: value update ho gayi

        // 2) get
        System.out.println(marks.get("Amit"));          // 85
        System.out.println(marks.get("Zoya"));          // null (key nahi hai)
        System.out.println(marks.getOrDefault("Zoya", 0));   // 0

        // 3) check
        System.out.println(marks.containsKey("Riya"));  // true
        System.out.println(marks.containsValue(60));    // false bcz no value implemented

        // 4) size, isEmpty
        System.out.println(marks.size());               // 3
        System.out.println(marks.isEmpty());            // false

        // 5) putIfAbsent: key na ho tabhi daalo
        marks.putIfAbsent("Karan", 100);                // Karan pehle se hai, 65 hi rahega
        marks.putIfAbsent("Neha", 70);                  // naya add hua

        // 6) remove
        marks.remove("Karan");

        // 7) loop: keys, values, dono
        for (String name : marks.keySet()) {
            System.out.println("Key: " + name);
        }
        for (int m : marks.values()) {
            System.out.println("Value: " + m);
        }
        for (Map.Entry<String, Integer> e : marks.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
        // Print order guaranteed nahi, tere machine pe alag ho sakta hai

        // 8) clear
        marks.clear();
        System.out.println(marks.isEmpty());            // true
    }
}
