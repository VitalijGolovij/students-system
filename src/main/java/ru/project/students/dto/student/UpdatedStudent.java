package ru.project.students.dto.student;

import lombok.Data;
import ru.project.students.dto.student.field.Contact;
import ru.project.students.dto.student.field.Git;
import ru.project.students.dto.student.field.PersonalData;

@Data
public class UpdatedStudent {
    private Contact contact;
    private Git git;
    private PersonalData personalData;
}
