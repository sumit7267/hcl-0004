package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // 1. Spring Container start karte hain AnnotationConfigApplicationContext ke sath
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        // 2. Container se StudentService bean ko retrieve karte hain
        StudentService service = context.getBean(StudentService.class);

        // 3. Method call karke details print karte hain
        service.displayStudentProfile();

        // 4. Container close karte hain
        context.close();
    }
}