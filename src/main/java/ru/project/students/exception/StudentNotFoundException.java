package ru.project.students.exception;

public class StudentNotFoundException extends RuntimeException{
    private static final String MST_TEMPLATE = "Student with id = %d not found";
    public StudentNotFoundException(Long id){
        super(String.format(MST_TEMPLATE, id));
    }
}
