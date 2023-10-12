package ru.project.students.dto.response;

import lombok.Data;
import lombok.Getter;

@Getter
public class GetStudentCountResponse extends ResultResponse{
    private long resultData;
    public GetStudentCountResponse(long count) {
        super("OK");
        this.resultData = count;
    }
}
