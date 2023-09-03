package ru.project.students.service;

import org.springframework.data.domain.Pageable;
import ru.project.students.dto.student.StudentPagination;

public interface PageService {
    Pageable getPageable(StudentPagination pagination);
}
