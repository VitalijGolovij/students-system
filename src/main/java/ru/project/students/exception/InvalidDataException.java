package ru.project.students.exception;

import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class InvalidDataException extends RuntimeException{
    private final List<Map<String, String>> errorsField;
    public InvalidDataException(List<Map<String, String>> errorsField){
        super("Invalid data exception");
        this.errorsField = errorsField;
    }
}
