package ru.project.students.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;
import ru.project.students.model.Student;

@Component
public class StudentFilter {
    public ExampleMatcher getMatcher(){
        return ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnorePaths("id");
    }

    public Example<Student> getStudentExample(Student student){
        return Example.of(student, getMatcher());
    }

    public Example<Student> getEmptyExample(){
        Student student = new Student();
        return Example.of(student, emptyMatcher());
    }

    public ExampleMatcher emptyMatcher(){
        return ExampleMatcher.matchingAll();
    }
}
