package ru.project.students.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.project.students.model.Student;
import ru.project.students.model.field.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class SpecificationService {
    public Specification<Student> getSearchStudentSpecification(ru.project.students.dto.student.StudentDto student){
        return ((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (student.getPersonalData() != null) {
                processPersonalData(predicates, student.getPersonalData(), root, builder);
            }
            if (student.getFContact() != null) {
                processContact(predicates, student.getFContact(), root, builder);
            }
            if (student.getFGit() != null) {
                processGit(predicates, student.getFGit(), root.get("fGit"), builder);
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        });
    }

    public Specification<Student> emptySpec(){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.conjunction();
        };
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
            if(personalData.getLastnameInitials() != null){
                String lastname = personalData.getLastnameInitials().split(" ")[0];

                Predicate like = builder.like(
                        root.get("personalData").get("lastname"),
                        "%"+lastname+"%"
                );
                predicates.add(like);
            }
        }
    }
    private void processContact(List<Predicate> predicates, Contact contact,
                                Root<Student> root, CriteriaBuilder builder){
        if (contact.getHasContact() != null && contact.getHasContact()){
            Predicate exist = builder.or(
                    builder.isNotNull(root.get("fContact").get("fPhone").get("phone")),
                    builder.isNotNull(root.get("fContact").get("fEmail").get("email")),
                    builder.isNotNull(root.get("fContact").get("fTelegram").get("telegram"))
                    );
            predicates.add(exist);
        }
        if (contact.getFPhone() != null){
            processPhone(predicates, contact.getFPhone(),
                    root.get("fContact").get("fPhone"), builder);
        }
        if (contact.getFEmail() != null){
            processEmail(predicates, contact.getFEmail(),
                    root.get("fContact").get("fEmail"), builder);
        }
        if (contact.getFTelegram() != null){
            processEmail(predicates, contact.getFTelegram(),
                    root.get("fContact").get("fTelegram"), builder);
        }
    }
    private void processGit(List<Predicate> predicates, Git git,
                            Path<Git> root, CriteriaBuilder builder){
        String gitVal = git.getGit() != null ? git.getGit() : "";
        boolean hasGit = git.getHasGit() != null ? git.getHasGit() : true;

        if (hasGit){
            Predicate exist = builder.isNotNull(root.get("git"));
            predicates.add(exist);

            Predicate like = builder.like(
                    root.get("git"), "%"+gitVal+"%"
            );
            predicates.add(like);
        } else {
            Predicate notExist = builder.isNull(root);
            predicates.add(notExist);
        }
    }

    private void processPhone(List<Predicate> predicates, Phone phone,
                              Path<Phone> root, CriteriaBuilder builder){
        String phoneVal = phone.getPhone() != null ? phone.getPhone() : "";
        boolean hasPhone = phone.getHasPhone() != null ? phone.getHasPhone() : true;

        if (hasPhone){
            Predicate exist = builder.isNotNull(root.get("phone"));
            predicates.add(exist);

            Predicate like = builder.like(
                    root.get("phone"), "%"+phoneVal+"%"
            );
            predicates.add(like);
        } else {
            Predicate notExist = builder.isNull(root);
            predicates.add(notExist);
        }
    }

    private void processEmail(List<Predicate> predicates, Email email,
                              Path<Email> root, CriteriaBuilder builder){
        String emailVal = email.getEmail() != null ? email.getEmail() : "";
        boolean hasEmail = email.getHasEmail() != null ? email.getHasEmail() : true;

        if (hasEmail){
            Predicate exist = builder.isNotNull(root.get("email"));
            predicates.add(exist);

            Predicate like = builder.like(
                    root.get("email"), "%"+emailVal+"%"
            );
            predicates.add(like);
        } else {
            Predicate notExist = builder.isNull(root);
            predicates.add(notExist);
        }
    }

    private void processEmail(List<Predicate> predicates, Telegram telegram,
                              Path<Telegram> root, CriteriaBuilder builder){
        String telegramVal = telegram.getTelegram() != null ? telegram.getTelegram() : "";
        boolean hasTelegram = telegram.getHasTelegram() != null ? telegram.getHasTelegram() : true;

        if (hasTelegram){
            Predicate exist = builder.isNotNull(root.get("telegram"));
            predicates.add(exist);

            Predicate like = builder.like(
                    root.get("telegram"), "%"+telegramVal+"%"
            );
            predicates.add(like);
        } else {
            Predicate notExist = builder.isNull(root);
            predicates.add(notExist);
        }
    }
}
