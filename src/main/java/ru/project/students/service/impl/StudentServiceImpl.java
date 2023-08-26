package ru.project.students.service.impl;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import ru.project.students.convertor.StudentConvertor;
import ru.project.students.dto.request.CreateStudentRequest;
import ru.project.students.dto.request.GetStudentListRequest;
import ru.project.students.dto.student.StudentDto;
import ru.project.students.dto.student.StudentPagination;
import ru.project.students.exception.InvalidDataException;
import ru.project.students.model.Student;
import ru.project.students.repository.StudentRepository;
import ru.project.students.service.StudentService;
import java.util.*;

@RequiredArgsConstructor
@Service
@Validated
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final StudentConvertor studentConvertor;

    @Override
    public List<Student> getStudentList(GetStudentListRequest getStudentListRequest) {
        StudentPagination pagination = getStudentListRequest.getStudentPagination();
        Pageable pageable = setPageable(pagination);
        return studentRepository.findAll(pageable).getContent();
    }

    @Transactional
    @Override
    public Student createStudent(CreateStudentRequest createStudentRequest,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<Map<String,String>> errorList = processValidErrors(bindingResult);
            throw new InvalidDataException(errorList);
        }
        Student student = studentConvertor.toStudent(createStudentRequest.getStudent());
        studentRepository.save(student);
        return student;
    }

    @Override
    public Student getStudent(Long id) {
        return studentRepository.getStudentsById(id);
    }

    @Override
    @Transactional
    public Student putStudent(Long id, StudentDto studentDto,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            List<Map<String,String>> errorList = processValidErrors(bindingResult);
            throw new InvalidDataException(errorList);
        }
        Student student = getStudent(id);
        Student updatedStudent = studentConvertor.toStudent(studentDto);

        modelMapper.map(updatedStudent, student);
        student.setId(id);

        studentRepository.save(student);
        return student;
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

    private Pageable setPageable(StudentPagination pagination){
        if (pagination.getRandom() == null || !pagination.getRandom()){

            if (pagination.getSort() != null){
                Sort sort = Sort.by(
                        pagination.getOrderDesc() ?
                        Sort.Order.desc(pagination.getSort()) : Sort.Order.asc(pagination.getSort())
                );
                return PageRequest.of(pagination.getNumber(), pagination.getCount(), sort);
            } else {
                return PageRequest.of(pagination.getNumber(), pagination.getCount());
            }
        } else {
            return PageRequest.of(0, pagination.getCount(), Sort.by("function('RAND')"));
        }
    }
}
