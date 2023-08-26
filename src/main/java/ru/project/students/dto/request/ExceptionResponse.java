package ru.project.students.dto.request;

import lombok.Getter;
import ru.project.students.dto.response.ResultResponse;

@Getter
public class ExceptionResponse extends ResultResponse {

    private final String message;
    public ExceptionResponse(String code, String message) {
        super(code);
        this.message = message;
    }
}
