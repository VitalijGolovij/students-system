package ru.project.students.dto.student;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ru.project.students.dto.student.field.Contact;
import ru.project.students.dto.student.field.Git;
import ru.project.students.dto.student.field.PersonalData;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.Valid;
import java.util.Objects;

@Embeddable
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {
    @Embedded @Valid
    private Contact fContact;
    @Embedded @Valid
    private Git fGit;
    @Embedded @Valid
    private PersonalData personalData;
}
