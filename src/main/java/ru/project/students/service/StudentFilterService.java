package ru.project.students.service;

import org.springframework.data.domain.Example;
import ru.project.students.model.Student;

public interface StudentFilterService {
    Example<Student> getExample(Student studentExample);
}
