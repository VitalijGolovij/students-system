package ru.project.students.dto.student;

import lombok.Data;
import ru.project.students.dto.student.field.Contact;
import ru.project.students.dto.student.field.Git;
import ru.project.students.dto.student.field.PersonalData;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.Valid;

@Embeddable
@Data
public class StudentDto {
    @Embedded
    @Valid
    private Contact fContact;
    @Embedded @Valid
    private Git fGit;
    @Embedded @Valid
    private PersonalData personalData;
}
