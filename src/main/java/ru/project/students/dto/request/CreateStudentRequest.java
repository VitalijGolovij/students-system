package ru.project.students.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.project.students.dto.student.StudentDto;
import javax.persistence.Embedded;
import javax.validation.Valid;


@EqualsAndHashCode(callSuper = true)
@Data
public class CreateStudentRequest extends RequestBody{
    @Embedded @Valid
    private StudentDto student;
}
