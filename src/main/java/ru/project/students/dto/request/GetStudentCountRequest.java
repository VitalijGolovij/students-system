package ru.project.students.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.project.students.dto.student.StudentDto;

@Setter
@Getter
public class GetStudentCountRequest extends RequestBody{
    private StudentDto filter;
}
