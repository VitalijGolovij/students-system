package ru.project.students.service.impl;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import ru.project.students.convertor.StudentConvertor;
import ru.project.students.dto.request.CreateStudentRequest;
import ru.project.students.dto.request.GetStudentCountRequest;
import ru.project.students.dto.request.GetStudentListRequest;
import ru.project.students.dto.request.PutStudentRequest;
import ru.project.students.dto.student.StudentDto;
import ru.project.students.dto.student.StudentPagination;
import ru.project.students.exception.InvalidDataException;
import ru.project.students.exception.StudentNotFoundException;
import ru.project.students.model.Student;
import ru.project.students.repository.StudentRepository;
import ru.project.students.service.PageService;
import ru.project.students.service.StudentFilterService;
import ru.project.students.service.StudentService;
import java.util.*;

@RequiredArgsConstructor
@Service
@Validated
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final PageService pageServiceImpl;
    private final StudentFilterService studentFilterImpl;
    private final StudentConvertor studentConvertor;

    @Override
    public List<Student> getStudentList(GetStudentListRequest getStudentListRequest) {
        if (getStudentListRequest == null)
            return studentRepository.findAll();

        StudentDto filterDto = getStudentListRequest.getFilter();
        StudentPagination pagination = getStudentListRequest.getPagination();

        Student studentExample = studentConvertor.toStudent(filterDto);
        Example<Student> example = studentFilterImpl.getExample(studentExample);

        if (pagination != null) {
            if (pagination.getRandom() != null && pagination.getRandom()) {
                return getRandomList(example, pagination);
            }
        }
        Pageable pageable = pageServiceImpl.getPageable(pagination);

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
        StudentDto studentDto = createStudentRequest.getStudent();
        Student student = studentConvertor.toStudent(studentDto);
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
    public Student putStudent(Long id, PutStudentRequest request,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            List<Map<String,String>> errorList = processValidErrors(bindingResult);
            throw new InvalidDataException(errorList);
        }
        Student updatingStudent = getStudent(id);
        StudentDto updatedStudentDto = request.getStudent();
        Student updatedStudent = studentConvertor.toStudent(updatedStudentDto);

        modelMapper.map(updatedStudent, updatingStudent);
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

    @Override
    public long getStudentCount(GetStudentCountRequest request) {
        if (request == null){
            return studentRepository.count();
        }
        GetStudentListRequest studentListRequest = GetStudentListRequest.builder()
                .filter(request.getFilter())
                .build();
        return getStudentList(studentListRequest).size();
    }

    private List<Student> getRandomList(Example<Student> example, StudentPagination pagination){
        Pageable pageable = Pageable.unpaged();
        List<Student> result = studentRepository.findAll(example, pageable).getContent();
        result = randomOf(pagination.getCount(), result);
        return result;
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
        List<Student> tmpList = new ArrayList<>(source);
        Collections.shuffle(tmpList);
        if (tmpList.size() <= count){
            return tmpList;
        } else {
            return tmpList.subList(0, count);
        }
    }
}
