package OOPS;

import java.util.*;
import java.util.function.*;

public class LambdaDemo {

    // Apna khud ka functional interface (sirf ek abstract method)
    @FunctionalInterface
    interface MathOperation {
        int operate(int a, int b);
    }

    @FunctionalInterface
    interface Greeting {
        void greet(String name);
    }

    // Lambda ko method ke argument ke roop mein pass karna
    static int compute(int x, int y, MathOperation operation) {
        return operation.operate(x, y);
    }

    public static void main(String[] args) {

        // 1. Custom functional interface with lambda
        MathOperation add = (a, b) -> a + b;
        MathOperation multiply = (a, b) -> a * b;

        // Multi-line lambda: braces aur return zaroori
        MathOperation divide = (a, b) -> {
            if (b == 0) {
                throw new ArithmeticException("Cannot divide by zero");
            }
            return a / b;
        };

        System.out.println("Add: " + add.operate(10, 5));
        System.out.println("Multiply: " + multiply.operate(10, 5));
        System.out.println("Divide: " + divide.operate(10, 5));

        // 2. Lambda ko argument ki tarah pass karna
        System.out.println("Subtract: " + compute(8, 2, (a, b) -> a - b));

        // 3. Void method wala interface
        Greeting greeting = name -> System.out.println("Hello, " + name + "!");
        greeting.greet("Bhai");

        // 4. Built-in functional interfaces
        Function<Integer, Integer> square = n -> n * n;
        Predicate<String> isEmpty = s -> s.isEmpty();
        Consumer<String> printer = s -> System.out.println("Consumer: " + s);
        Supplier<String> supplier = () -> "Supplier se aaya value";
        BiFunction<Integer, Integer, Integer> max = (a, b) -> Math.max(a, b);

        System.out.println("Square of 5: " + square.apply(5));
        System.out.println("Is empty: " + isEmpty.test(""));
        printer.accept("Java Lambda");
        System.out.println(supplier.get());
        System.out.println("Max of 7 and 12: " + max.apply(7, 12));

        // 5. Runnable with lambda
        Runnable task = () -> System.out.println("Runnable chal raha hai");
        task.run();

        // 6. Comparator with lambda (sort by length)
        List<String> names = new ArrayList<>(List.of("Priya", "Aman", "Karan", "Ri"));
        names.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
        System.out.println("Sorted by length: " + names);

        // 7. forEach with lambda and method reference
        names.forEach(n -> System.out.println("Name: " + n));
        names.forEach(System.out::println);
    }
}
