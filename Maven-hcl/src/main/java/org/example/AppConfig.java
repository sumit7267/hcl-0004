package org.example;
import org.springframework.context.annotation.Bean;
import org.springframework.context.container.bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class AppConfig {
    @Bean
    public Employee employee(){
        return new Employee();
    }
}
