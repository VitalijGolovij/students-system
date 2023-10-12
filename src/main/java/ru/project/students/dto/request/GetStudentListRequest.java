package ru.project.students.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import ru.project.students.dto.student.StudentDto;
import ru.project.students.dto.student.StudentPagination;

import javax.validation.Valid;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class GetStudentListRequest extends RequestBody{

    private StudentDto filter;
    @Valid
    private StudentPagination pagination;
}
