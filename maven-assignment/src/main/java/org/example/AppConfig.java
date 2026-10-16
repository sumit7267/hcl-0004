package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Course course() {
        return new Course("C01", "Java Full Stack", 4);
    }

    @Bean
    public Student student(Course course) {
        // Spring automatically container se 'course' bean ko yahan inject kar dega
        return new Student(101, "Sumit Agnihotri", course);
    }

    @Bean
    public StudentService studentService(Student student) {
        // Spring automatically container se 'student' bean ko yahan inject kar dega
        return new StudentService(student);
    }
}