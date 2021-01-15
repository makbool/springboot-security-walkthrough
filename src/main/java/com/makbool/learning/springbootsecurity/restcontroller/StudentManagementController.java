package com.makbool.learning.springbootsecurity.restcontroller;

import com.makbool.learning.springbootsecurity.domain.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/api/students")
public class StudentManagementController {

    private static final List<Student> STUDENT_LIST = Arrays.asList(
            new Student(1, "Ram"),
            new Student(2, "Robert"),
            new Student(2, "Rahim")

    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_TRAINEE')")
    public List<Student> getAllStudents() {
        return STUDENT_LIST;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student student) {
        System.out.println("Registering : " + student);
    }

    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Integer studentId) {
        System.out.println("Deleting Student with Id : " + studentId);
    }

    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId") Integer studentId,
                              @RequestBody Student student) {
        System.out.println("Updating Student with Id : " + studentId +
                " with details " + student);
    }

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId) {
        return STUDENT_LIST.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("Student " + studentId + " not available"));
    }

}
