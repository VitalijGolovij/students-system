package ru.project.students.dto.response;

import lombok.Data;
import lombok.Getter;

@Getter
public class ResultResponse {
    private final String code;
    public ResultResponse(String code){
        this.code = code;
    }
}
