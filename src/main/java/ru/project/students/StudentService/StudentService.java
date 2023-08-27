package ru.project.students.StudentService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.project.students.dto.StudentPagination;
import ru.project.students.model.Student;
import ru.project.students.repository.StudentRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getAllStudent(Student student){
        Specification<Student> spec = StudentSpecification.getSpec(student);
        return studentRepository.findAll(spec);
    }
}
