package org.example;

public class StudentService {
    private Student student; // Dependency

    // Constructor Injection for Student
    public StudentService(Student student) {
        this.student = student;
    }

    public void displayStudentProfile() {
        student.displayStudentDetails();
    }
}