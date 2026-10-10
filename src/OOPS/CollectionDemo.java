package OOPS;

import java.util.*;
public class CollectionDemo {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Amit"); names.add("Riya"); names.add("Amit");
        System.out.println(names); // [Amit, Riya, Amit]
        Set<String> unique = new HashSet<>(names);
        System.out.println(unique.size()); // 2 (duplicate hat gaya)
        Map<String, Integer> marks = new HashMap<>();
        marks.put("Amit", 80);
        marks.put("Riya", 92);
        for (Map.Entry<String, Integer> e : marks.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
        System.out.println(marks.getOrDefault("Karan", 0)); // 0
    }
}
