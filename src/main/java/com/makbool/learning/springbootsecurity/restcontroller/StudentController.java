package com.makbool.learning.springbootsecurity.restcontroller;



import com.makbool.learning.springbootsecurity.domain.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private static final List<Student> STUDENT_LIST = Arrays.asList(
            new Student(1, "Ram"),
            new Student(2, "Robert"),
            new Student(2, "Rahim")

    );

    @GetMapping(path = "/{studentId}")
    public Student getStudentDetails(@PathVariable("studentId") Integer studentId) {
        return STUDENT_LIST.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("Student " + studentId + " not available"));
    }

}
