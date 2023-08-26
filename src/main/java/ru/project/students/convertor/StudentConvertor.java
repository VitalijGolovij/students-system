package ru.project.students.convertor;

import ru.project.students.dto.student.StudentDto;
import ru.project.students.model.Student;

public interface StudentConvertor {
    public Student toStudent(StudentDto studentDto);
    public StudentDto toStudentDto(Student student);
}
