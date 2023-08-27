package ru.project.students.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.students.StudentService.StudentService;
import ru.project.students.model.Student;
import ru.project.students.repository.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @PostMapping
    public List<Student> getAll(@RequestBody Student student){
        return studentService.getAllStudent(student);
    }
}
