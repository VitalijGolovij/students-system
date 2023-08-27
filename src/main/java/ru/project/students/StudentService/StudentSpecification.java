package ru.project.students.StudentService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.project.students.model.Student;
import ru.project.students.repository.StudentRepository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class StudentSpecification {
    private final StudentRepository studentRepository;
    public static Specification<Student> getSpec(Student student){
        return ((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(student.getPersonalData() != null){
                Predicate like = builder.like(root.get("personalData").get("firstname"),
                        "%"+student.getPersonalData().getFirstname()+"%");
                predicates.add(like);
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
