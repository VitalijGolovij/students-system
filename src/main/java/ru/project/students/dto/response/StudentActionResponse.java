package ru.project.students.dto.response;

import lombok.Getter;
import ru.project.students.model.Student;

import java.util.List;

@Getter
public class StudentActionResponse extends ResultResponse{
    List<Student> info;
    public StudentActionResponse(List<Student> info) {
        super("Ok");
        this.info = info;
    }
}
