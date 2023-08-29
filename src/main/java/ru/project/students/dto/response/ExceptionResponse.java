package ru.project.students.dto.response;

import lombok.Getter;

@Getter
public class ExceptionResponse extends ResultResponse {

    private final String message;
    public ExceptionResponse(String code, String message) {
        super(code);
        this.message = message;
    }
}
