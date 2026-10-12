package OOPS;

import java.util.*;

class StudentRecord implements Comparable<StudentRecord> {
    private final String name;
    private final int rollNo;
    private final int age;
    private final double marks;

    StudentRecord(String name, int rollNo, int age, double marks) {
        this.name = name;
        this.rollNo = rollNo;
        this.age = age;
        this.marks = marks;
    }

    public int getAge() { return age; }
    public double getMarks() { return marks; }

    // Comparable: roll number ke hisaab se sort
    @Override
    public int compareTo(StudentRecord other) {
        return Integer.compare(this.rollNo, other.rollNo);
    }

    @Override
    public String toString() {
        return name + " " + rollNo + " " + age + " " + marks;
    }
}

public class ComparableAndComparator {

    public static void main(String[] args) {
        List<StudentRecord> students = new ArrayList<>();
        students.add(new StudentRecord("Aman", 104, 21, 85.5));
        students.add(new StudentRecord("Riya", 101, 20, 92.0));
        students.add(new StudentRecord("Karan", 103, 22, 78.5));
        students.add(new StudentRecord("Priya", 102, 20, 95.0));
        students.add(new StudentRecord("Rahul", 105, 21, 88.0));

        // Comparable: roll number
        Collections.sort(students);
        print("Sorted by Roll Number (Comparable):", students);

        // Comparator: age ascending
        students.sort(Comparator.comparingInt(StudentRecord::getAge));
        print("Sorted by Age (Comparator):", students);

        // Comparator: marks descending
        students.sort(Comparator.comparingDouble(StudentRecord::getMarks).reversed());
        print("Sorted by Marks (Descending):", students);
    }

    private static void print(String title, List<StudentRecord> students) {
        System.out.println("\n" + title);
        students.forEach(System.out::println);
    }
}

