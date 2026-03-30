package com.example.studentapp.config;

import com.example.studentapp.entity.Student;
import com.example.studentapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component  // Disabled - insert data manually via API
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        // Clear existing data
        studentRepository.deleteAll();

        // Add sample students
        Student student1 = new Student();
        student1.setName("John Doe");
        student1.setEmail("john@example.com");
        student1.setAge(20);

        Student student2 = new Student();
        student2.setName("Jane Smith");
        student2.setEmail("jane@example.com");
        student2.setAge(21);

        Student student3 = new Student();
        student3.setName("Mike Johnson");
        student3.setEmail("mike@example.com");
        student3.setAge(22);

        Student student4 = new Student();
        student4.setName("Sarah Williams");
        student4.setEmail("sarah@example.com");
        student4.setAge(20);

        // Save all students
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);

        System.out.println("✓ Sample data loaded successfully!");
    }
}
