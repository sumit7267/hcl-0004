package coreJavaFoundation;

import java.util.*;
import java.util.stream.*;
public class Java8Demo {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(5, 12, 8, 21, 3, 16);
        List<Integer> evenSquares = nums.stream()
                .filter(n -> n % 2 == 0) // lambda: condition
                .map(n -> n * n) // transform
                .collect(Collectors.toList());
        System.out.println(evenSquares); // [144, 64, 256]
        int sum = nums.stream().mapToInt(Integer::intValue).sum(); // method reference
        System.out.println(sum); // 65
        Optional<String> name = Optional.of("Java");
        System.out.println(name.orElse("empty")); // Java
        Optional<String> none = Optional.empty();
        System.out.println(none.orElse("empty")); // empty
    }
}
