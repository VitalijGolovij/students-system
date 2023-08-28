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
import ru.project.students.dto.student.StudentDto;
import ru.project.students.dto.student.StudentPagination;
import ru.project.students.exception.InvalidDataException;
import ru.project.students.exception.StudentNotFoundException;
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
        StudentDto filter = getStudentListRequest.getFilter();
        StudentPagination pagination = getStudentListRequest.getPagination();

        Specification<Student> spec = filter == null ? specificationService.emptySpec() :
                specificationService.getSearchStudentSpecification(filter);

        if (pagination == null){
            return studentRepository.findAll(spec);
        }
        if (pagination.getRandom() != null && pagination.getRandom()){
            List<Student> studentList = studentRepository.findAll(spec);
            return randomOf(pagination.getCount(), studentList);
        }
        Pageable pageable = pageService.getPageableSort(pagination);

        return studentRepository.findAll(spec, pageable).getContent();
    }

    @Transactional
    @Override
    public Student createStudent(CreateStudentRequest createStudentRequest,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<Map<String,String>> errorList = processValidErrors(bindingResult);
            throw new InvalidDataException(errorList);
        }
        Student student = createStudentRequest.getStudent();
        studentRepository.save(student);
        return student;
    }

    @Override
    public Student getStudent(Long id) {
        Optional<Student> student = studentRepository.getStudentsById(id);
        if (student.isEmpty()){
            throw new StudentNotFoundException(id);
        }
        return student.get();
    }

    @Override
    @Transactional
    public Student putStudent(Long id, Student student,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            List<Map<String,String>> errorList = processValidErrors(bindingResult);
            throw new InvalidDataException(errorList);
        }
        Student updatingStudent = getStudent(id);
        modelMapper.map(student, updatingStudent);
        updatingStudent.setId(id);

        studentRepository.save(updatingStudent);

        return updatingStudent;
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

    private List<Student> randomOf(Integer count, List<Student> source){
        Collections.shuffle(source);
        if (source.size() < count){
            return source;
        } else {
            return source.subList(0, count);
        }
    }
}
