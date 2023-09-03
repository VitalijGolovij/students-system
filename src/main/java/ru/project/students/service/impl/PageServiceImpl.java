package ru.project.students.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.project.students.dto.student.StudentPagination;
import ru.project.students.service.PageService;

@Service
public class PageServiceImpl implements PageService {
    @Override
    public Pageable getPageable(StudentPagination pagination){
        if (pagination == null)
            return Pageable.unpaged();
        Sort sort = getSort(pagination);
        return PageRequest.of(pagination.getNumber(), pagination.getCount(), sort);
    }
    private Sort getSort(StudentPagination pagination){
        String fieldName = pagination.getSort() != null ?
                pagination.getSort() : "lastname";
        boolean sortDesc = pagination.getOrderDesc() != null ?
                pagination.getOrderDesc() : false;

        if (!sortDesc){
            return Sort.by(fieldName).ascending();
        } else {
            return Sort.by(fieldName).descending();
        }
    }
}
