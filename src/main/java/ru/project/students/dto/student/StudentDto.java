package ru.project.students.dto.student;

import lombok.Data;
import ru.project.students.model.field.Contact;
import ru.project.students.model.field.Git;
import ru.project.students.model.field.PersonalData;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Data
public class StudentDto {
    @Embedded
    private Contact fContact;
    @Embedded
    private Git fGit;
    @Embedded
    private PersonalData personalData;
}
