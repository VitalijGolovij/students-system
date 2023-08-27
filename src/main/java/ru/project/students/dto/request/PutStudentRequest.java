package ru.project.students.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.project.students.dto.student.StudentSearch;
import ru.project.students.model.Student;

import javax.validation.Valid;

@EqualsAndHashCode(callSuper = true)
@Data
public class PutStudentRequest extends RequestBody{
    @Valid
    private Student student;
}
