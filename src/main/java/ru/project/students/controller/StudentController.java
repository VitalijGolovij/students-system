package ru.project.students.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.project.students.dto.request.CreateStudentRequest;
import ru.project.students.dto.request.GetStudentListRequest;
import ru.project.students.dto.request.PutStudentRequest;
import ru.project.students.dto.response.StudentActionResponse;
import ru.project.students.dto.student.StudentDto;
import ru.project.students.model.Student;
import ru.project.students.repository.StudentRepository;
import ru.project.students.service.StudentFilter;
import ru.project.students.service.StudentService;
import ru.project.students.service.impl.SpecificationService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/students/general")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final StudentFilter studentFilter;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final SpecificationService specificationService;

    @PostMapping("/get-student-list")
    public StudentActionResponse getStudentList(@RequestBody GetStudentListRequest getStudentListRequest){
        //валидировать запрос
        List<Student> studentList = studentService.getStudentList(getStudentListRequest);
        return new StudentActionResponse(studentList);
    }

    @PostMapping
    public StudentActionResponse createStudent(@RequestBody @Valid CreateStudentRequest createStudentRequest,
                                               BindingResult bindingResult){
        Student savedStudent = studentService.createStudent(createStudentRequest, bindingResult);
        return new StudentActionResponse(Collections.singletonList(savedStudent));
    }

    @GetMapping("/{id}")
    public StudentActionResponse getStudent(@PathVariable Long id){
        Student student = studentService.getStudent(id);
        return new StudentActionResponse(Collections.singletonList(student));
    }

    @PutMapping("/{id}")
    public StudentActionResponse putStudent(@PathVariable Long id, @RequestBody @Valid PutStudentRequest request,
                                            BindingResult bindingResult){
        Student updatedStudent = studentService.putStudent(id, request.getStudent(), bindingResult);
        return new StudentActionResponse(Collections.singletonList(updatedStudent));
    }

    @DeleteMapping("/{id}")
    public StudentActionResponse deleteStudent(@PathVariable Long id){
        Student deletedStudent = studentService.deleteStudent(id);
        return new StudentActionResponse(Collections.singletonList(deletedStudent));
    }
}
