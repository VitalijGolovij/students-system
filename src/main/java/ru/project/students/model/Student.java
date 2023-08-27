package ru.project.students.model;

import lombok.Data;
import ru.project.students.model.field.Contact;
import ru.project.students.model.field.Git;
import ru.project.students.model.field.PersonalData;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Table(name = "students")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Embedded @Valid
    private Contact fContact;
    @Embedded @Valid
    private Git fGit;
    @Embedded @Valid
    private PersonalData personalData;

    //PostLoad
}
