package ru.project.students.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.project.students.dto.student.StudentPagination;

@Service
public class PageService {
    public Pageable getPageableSort(StudentPagination pagination){
        Sort sort = getSort(pagination);
        return PageRequest.of(pagination.getNumber(), pagination.getCount(), sort);
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
        String fieldName = pagination.getSort() != null ?
                pagination.getSort() : "personalData.lastname";
        boolean sortDesc = pagination.getOrderDesc() != null ?
                pagination.getOrderDesc() : false;

        Sort sort = null;

        if (fieldName.equals("contact")){
            sort = Sort.by("fContact.fPhone.phone")
                    .and(Sort.by("fContact.fTelegram.telegram"))
                    .and(Sort.by("fContact.fEmail.email"));
        } else if (fieldName.equals("personalData.lastnameInitials")){
            sort = Sort.by("personalData.lastname", "personalData.firstname");
        } else {
            sort = Sort.by(fieldName);
        }

        if (!sortDesc){
            return sort.ascending();
        } else {
            return sort.descending();
        }
    }
}
