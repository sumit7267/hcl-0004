package OOPS;

import java.util.*;

class Student {
    private int id;
    private String name;
    private int age;
    private double marks;

    Student(int id, String name, int age, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    public int getId()       { return id; }
    public String getName()  { return name; }
    public int getAge()      { return age; }
    public double getMarks() { return marks; }

    @Override
    public String toString() {
        return String.format("%-4d %-8s %-4d %.1f", id, name, age, marks);
    }
}
   class Main {




    static void printList(String title, List<Student> list) {
        System.out.println("\n=== " + title + " ===");
        System.out.println("ID   Name     Age  Marks");
        for (Student s : list) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        // 1) 10 students add karna
        students.add(new Student(1, "Amit", 21, 78.5));
        students.add(new Student(2, "Riya", 19, 91.0));
        students.add(new Student(3, "Karan", 22, 65.0));
        students.add(new Student(4, "Neha", 20, 84.5));
        students.add(new Student(5, "Rahul", 23, 72.0));
        students.add(new Student(6, "Pooja", 19, 88.0));
        students.add(new Student(7, "Vikas", 21, 55.5));
        students.add(new Student(8, "Sonia", 20, 95.0));
        students.add(new Student(9, "Arjun", 22, 69.5));
        students.add(new Student(10, "Meera", 18, 81.0));

        // 2) Original list print
        printList("Original List", students);

        // 3) Age ke basis pe sort (chhote se bada)
        students.sort(Comparator.comparingInt(Student::getAge));
        printList("Sorted by Age (ascending)", students);

        // 4) Marks ke basis pe sort (zyada marks pehle)
        students.sort(Comparator.comparingDouble(Student::getMarks).reversed());
        printList("Sorted by Marks (highest first)", students);
    }
}
