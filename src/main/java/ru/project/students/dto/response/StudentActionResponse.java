package ru.project.students.dto.response;

import lombok.Getter;
import ru.project.students.dto.student.StudentDto;

import java.util.List;

@Getter
public class StudentActionResponse extends ResultResponse{
    List<StudentDto> info;
    public StudentActionResponse(List<StudentDto> info) {
        super("Ok");
        this.info = info;
    }
}
