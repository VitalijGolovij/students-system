package ru.project.students.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.project.students.dto.student.StudentDto;
import ru.project.students.dto.student.StudentPagination;

import javax.validation.Valid;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetStudentListRequest extends RequestBody{

    private StudentDto filter;
    @Valid
    private StudentPagination pagination;
}
