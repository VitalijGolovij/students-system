package ru.project.students.dto.student;

import lombok.Data;

@Data
public class StudentPagination {
    private Integer count;
    private Integer number;
    private Boolean random;
    private String sort;
    private Boolean orderDesc;
}
