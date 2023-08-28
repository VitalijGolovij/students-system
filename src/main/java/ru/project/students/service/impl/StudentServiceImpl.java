package ru.project.students.service.impl;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import ru.project.students.dto.request.CreateStudentRequest;
import ru.project.students.dto.request.GetStudentListRequest;
import ru.project.students.dto.student.StudentSearch;
import ru.project.students.dto.student.StudentPagination;
import ru.project.students.exception.InvalidDataException;
import ru.project.students.model.Student;
import ru.project.students.repository.StudentRepository;
import ru.project.students.service.StudentFilter;
import ru.project.students.service.StudentService;
import java.util.*;

@RequiredArgsConstructor
@Service
@Validated
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final SpecificationService specificationService;
    private final PageService pageService;
    private final StudentFilter studentFilter;

    @Override
    public List<Student> getStudentList(GetStudentListRequest getStudentListRequest) {
        Student filter = getStudentListRequest.getFilter();
        Student studentFilterExample = filter; // перевод из DTO
        StudentPagination pagination = getStudentListRequest.getStudentPagination();

        Example<Student> example = (filter != null) ?
                studentFilter.getStudentExample(studentFilterExample) : studentFilter.getEmptyExample();

        Pageable pageable = (pagination != null) ?
                pageService.getPageable(pagination) : Pageable.unpaged();

        return studentRepository.findAll(example, pageable).getContent();
    }

    @Transactional
    @Override
    public Student createStudent(CreateStudentRequest createStudentRequest,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<Map<String,String>> errorList = processValidErrors(bindingResult);
            throw new InvalidDataException(errorList);
        }
        return null;
    }

    @Override
    public Student getStudent(Long id) {
        return studentRepository.getStudentsById(id);
    }

    @Override
    @Transactional
    public Student putStudent(Long id, Student student,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            List<Map<String,String>> errorList = processValidErrors(bindingResult);
            throw new InvalidDataException(errorList);
        }
        return null;
    }

    @Override
    @Transactional
    public Student deleteStudent(Long id) {
        Student student = getStudent(id);
        studentRepository.delete(student);
        return student;
    }

    private List<Map<String, String>> processValidErrors(BindingResult bindingResult){
        List<FieldError> errors = bindingResult.getFieldErrors();
        List<Map<String, String>> errorList = new ArrayList<>();

        for (FieldError error: errors){
            errorList.add(Map.of("field",error.getField(),
                    "message", Objects.requireNonNull(error.getDefaultMessage())));
        }
        return  errorList;
    }
}
