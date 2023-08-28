package ru.project.students.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.project.students.dto.student.StudentPagination;

@Service
public class PageService {
    public Pageable getPageableSort(StudentPagination pagination){
        if (pagination.getSort() != null){
            Sort sort = getSort(pagination);

            return PageRequest.of(pagination.getNumber(), pagination.getCount(), sort);
        } else {
            return PageRequest.of(pagination.getNumber(), pagination.getCount());
        }
    }
    public Pageable getPageableRandom(StudentPagination pagination){
        return PageRequest.of(0, pagination.getCount(), Sort.by("function('RAND')"));
    }

    public Pageable getPageable(StudentPagination pagination){
        if (pagination.getRandom() == null || !pagination.getRandom()){
            return getPageableSort(pagination);
        } else {
            return getPageableRandom(pagination);
        }
    }

    private Sort getSort(StudentPagination pagination){
        String fieldName = pagination.getSort();
        Boolean sortDesc = pagination.getOrderDesc();

        Sort sort = null;

        //TODO рефакторинг
        if (fieldName.equals("contact")){
            sort = Sort.by("phone")
                    .and(Sort.by("telegram"))
                    .and(Sort.by("email"));
        } else if (fieldName.equals("lastnameInitials")){
            sort = Sort.by("lastname", "firstname");
        } else {
            sort = Sort.by(fieldName);
        }

        if (sortDesc == null || !sortDesc ){
            return sort.ascending();
        } else {
            return sort.descending();
        }
    }
}
