package ru.project.students.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.project.students.model.Student;

import javax.persistence.Embedded;
import javax.validation.Valid;


@EqualsAndHashCode(callSuper = true)
@Data
public class CreateStudentRequest extends RequestBody{
    @Embedded @Valid
    private Student student;
}
