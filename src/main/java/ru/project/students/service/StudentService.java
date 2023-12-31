package ru.project.students.service;

import org.springframework.validation.BindingResult;
import ru.project.students.dto.request.CreateStudentRequest;
import ru.project.students.dto.request.GetStudentCountRequest;
import ru.project.students.dto.request.GetStudentListRequest;
import ru.project.students.dto.request.PutStudentRequest;
import ru.project.students.dto.student.StudentDto;
import ru.project.students.model.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getStudentList(GetStudentListRequest getStudentListRequest);
    public Student createStudent(CreateStudentRequest createStudentRequest, BindingResult bindingResult);
    public Student getStudent(Long id);
    public Student putStudent(Long id, PutStudentRequest request, BindingResult bindingResult);
    public Student deleteStudent(Long id);
    public long getStudentCount(GetStudentCountRequest request);
}
