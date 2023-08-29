package ru.project.students.exception.handler;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.project.students.dto.response.ExceptionResponse;
import ru.project.students.dto.response.ResultResponse;
import ru.project.students.dto.response.ValidateExceptionResponse;
import ru.project.students.exception.InvalidDataException;
import ru.project.students.exception.StudentNotFoundException;

import java.net.SocketTimeoutException;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ResultResponse> handleException(StudentNotFoundException e){
        ResultResponse response = new ExceptionResponse("NotFound", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ExceptionHandler
    public ResponseEntity<ResultResponse> handleException(InvalidDataException e){
        ResultResponse response = new ValidateExceptionResponse("StrErr", e.getMessage(), e.getErrorsField());
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @ExceptionHandler
    public ResponseEntity<ResultResponse> handleException(PropertyReferenceException e){
        ResultResponse response = new ExceptionResponse("StrErr", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @ExceptionHandler
    public ResponseEntity<ResultResponse> handleException(SocketTimeoutException e){
        ResultResponse response = new ExceptionResponse("DbErr", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
