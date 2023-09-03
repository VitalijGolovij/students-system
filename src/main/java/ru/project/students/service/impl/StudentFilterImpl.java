package ru.project.students.service.impl;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;
import ru.project.students.model.Student;
import ru.project.students.service.StudentFilterService;

@Component
public class StudentFilterImpl implements StudentFilterService {
    @Override
    public Example<Student> getExample(Student studentExample){
        if (studentExample != null){
            return getStudentExample(studentExample);
        } else {
            return getEmptyExample();
        }
    }

    private ExampleMatcher getMatcher(){
        return ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnorePaths("id");
    }

    private Example<Student> getStudentExample(Student student){
        return Example.of(student, getMatcher());
    }

    private Example<Student> getEmptyExample(){
        Student student = new Student();
        return Example.of(student, emptyMatcher());
    }

    private ExampleMatcher emptyMatcher(){
        return ExampleMatcher.matchingAll();
    }
}
