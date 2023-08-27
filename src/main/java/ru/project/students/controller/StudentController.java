package ru.project.students.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.project.students.dto.request.GetStudentListRequest;
import ru.project.students.dto.request.CreateStudentRequest;
import ru.project.students.dto.request.PutStudentRequest;
import ru.project.students.dto.response.StudentActionResponse;
import ru.project.students.dto.student.StudentSearch;
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

    @PostMapping("/get-student-list")
    public List<Student> getStudentList(@RequestBody GetStudentListRequest getStudentListRequest){
        return studentService.getStudentList(getStudentListRequest);
    }

//    @PostMapping
//    public StudentActionResponse createStudent(@RequestBody @Valid CreateStudentRequest createStudentRequest,
//                                           BindingResult bindingResult){
//        Student savedStudent = studentService.createStudent(createStudentRequest, bindingResult);
//        StudentSearch studentForResponse = studentConvertor.toStudentDto(savedStudent);
//        return new StudentActionResponse(Collections.singletonList(studentForResponse));
//    }
//
//    @GetMapping("/{id}")
//    public StudentActionResponse getStudent(@PathVariable Long id){
//        Student student = studentService.getStudent(id);
//        StudentSearch studentForResponse = studentConvertor.toStudentDto(student);
//        return new StudentActionResponse(Collections.singletonList(studentForResponse));
//    }
//
//    @PutMapping("/{id}")
//    public StudentActionResponse putStudent(@PathVariable Long id, @RequestBody @Valid PutStudentRequest request,
//                                            BindingResult bindingResult){
//        Student updatedStudent = studentService.putStudent(id, request.getStudent(), bindingResult);
//        StudentSearch studentForResponse = studentConvertor.toStudentDto(updatedStudent);
//        return new StudentActionResponse(Collections.singletonList(studentForResponse));
//    }
//
//    @DeleteMapping("/{id}")
//    public StudentActionResponse deleteStudent(@PathVariable Long id){
//        Student deletedStudent = studentService.deleteStudent(id);
//        StudentSearch studentForResponse = studentConvertor.toStudentDto(deletedStudent);
//        return new StudentActionResponse(Collections.singletonList(studentForResponse));
//    }
}
