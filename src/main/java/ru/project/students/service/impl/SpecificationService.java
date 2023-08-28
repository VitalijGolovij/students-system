package ru.project.students.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.project.students.dto.student.StudentSearch;
import ru.project.students.model.Student;
import ru.project.students.model.field.Contact;
import ru.project.students.model.field.Git;
import ru.project.students.model.field.PersonalData;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class SpecificationService {
    public Specification<Student> getSearchStudentSpecification(StudentSearch student){
        return ((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (student.getPersonalData() != null) {
                processPersonalData(predicates, student.getPersonalData(), root, builder);
            }
            if (student.getFContact() != null) {
                processContact(predicates, student.getFContact(), root, builder);
            }
            if (student.getFGit() != null) {
                processGit(predicates, student.getFGit(), root, builder);
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        });
    }

    private void processPersonalData(List<Predicate> predicates, PersonalData personalData,
                                     Root<Student> root, CriteriaBuilder builder){
        if (personalData != null){
            if (personalData.getFirstname() != null){
                Predicate like = builder.like(
                        root.get("personalData").get("firstname"),
                        "%"+personalData.getFirstname()+"%"
                );
                predicates.add(like);
            }
            if (personalData.getLastname() != null){
                Predicate like = builder.like(
                        root.get("personalData").get("lastname"),
                        "%"+personalData.getLastname()+"%"
                );
                predicates.add(like);
            }
            if(personalData.getPatronymic() != null){
                Predicate like = builder.like(
                        root.get("personalData").get("patronymic"),
                        "%"+personalData.getPatronymic()+"%"
                );
                predicates.add(like);
            }
        }
    }
    private void processContact(List<Predicate> predicates, Contact contact,
                                Root<Student> root, CriteriaBuilder builder){
        if (contact.getFPhone() != null){
            String phone = contact.getFPhone().getPhone();

            Predicate like = builder.like(
                    root.get("fContact").get("fPhone").get("phone"),
                    "%"+phone+"%"
            );
            predicates.add(like);
        }
        if (contact.getFEmail() != null){
            String email = contact.getFEmail().getEmail();

            Predicate like = builder.like(
                    root.get("fContact").get("fEmail").get("email"),
                    "%"+email+"%"
            );
            predicates.add(like);
        }
        if (contact.getFTelegram() != null){
            String telegram = contact.getFTelegram().getTelegram();

            Predicate like = builder.like(
                    root.get("fContact").get("fTelegram").get("telegram"),
                    "%"+telegram+"%"
            );
            predicates.add(like);
        }
    }
    private void processGit(List<Predicate> predicates, Git git,
                            Root<Student> root, CriteriaBuilder builder){
        String gitStr = git.getGit();

        Predicate like = builder.like(
                root.get("fGit").get("git"),
                "%"+gitStr+"%"
        );
        predicates.add(like);
    }
}
