package ru.project.students.dto.student;

import lombok.Data;
import ru.project.students.model.field.Contact;
import ru.project.students.model.field.Git;
import ru.project.students.model.field.PersonalData;

@Data
public class UpdatedStudent {
    private Contact contact;
    private Git git;
    private PersonalData personalData;
}
