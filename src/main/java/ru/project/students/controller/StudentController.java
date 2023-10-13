package ru.project.students.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.project.students.convertor.StudentConvertor;
import ru.project.students.dto.request.CreateStudentRequest;
import ru.project.students.dto.request.GetStudentCountRequest;
import ru.project.students.dto.request.GetStudentListRequest;
import ru.project.students.dto.request.PutStudentRequest;
import ru.project.students.dto.response.GetStudentCountResponse;
import ru.project.students.dto.response.ResultResponse;
import ru.project.students.dto.response.StudentActionResponse;
import ru.project.students.dto.student.StudentDto;
import ru.project.students.model.Student;
import ru.project.students.service.StudentService;


import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/students/general")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final StudentConvertor studentConvertor;

    @PostMapping("/get-student-list")
    @CrossOrigin
    public StudentActionResponse getStudentList(@RequestBody @Nullable GetStudentListRequest request){
        List<Student> studentList = studentService.getStudentList(request);
        List<StudentDto> result = studentList.stream()
                .map(studentConvertor::toStudentDto)
                .toList();
        return new StudentActionResponse(result);
    }

    @PostMapping("/get-student-count")
    @CrossOrigin
    public ResultResponse getStudentCount(@RequestBody @Nullable GetStudentCountRequest request){
        long count = studentService.getStudentCount(request);
        return new GetStudentCountResponse(count);
    }

    @PostMapping
    @CrossOrigin
    public StudentActionResponse createStudent(@RequestBody @Valid CreateStudentRequest request,
                                               BindingResult bindingResult){
        Student savedStudent = studentService.createStudent(request, bindingResult);
        StudentDto studentDto = studentConvertor.toStudentDto(savedStudent);
        return new StudentActionResponse(Collections.singletonList(studentDto));
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public StudentActionResponse getStudent(@PathVariable Long id){
        Student student = studentService.getStudent(id);
        StudentDto studentDto = studentConvertor.toStudentDto(student);
        return new StudentActionResponse(Collections.singletonList(studentDto));
    }

    @PutMapping("/{id}")
    @CrossOrigin
    public StudentActionResponse putStudent(@PathVariable Long id, @RequestBody @Valid PutStudentRequest request,
                                            BindingResult bindingResult){
        studentService.putStudent(id, request, bindingResult);
        Student updatedStudent = studentService.getStudent(id);
        StudentDto studentDto = studentConvertor.toStudentDto(updatedStudent);
        return new StudentActionResponse(Collections.singletonList(studentDto));
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public StudentActionResponse deleteStudent(@PathVariable Long id){
        Student deletedStudent = studentService.deleteStudent(id);
        StudentDto studentDto = studentConvertor.toStudentDto(deletedStudent);
        return new StudentActionResponse(Collections.singletonList(studentDto));
    }
}
