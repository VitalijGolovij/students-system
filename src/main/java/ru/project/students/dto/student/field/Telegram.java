package ru.project.students.dto.student.field;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@Embeddable
public class Telegram {
    @Pattern(regexp = "^@[\\w\\d_]+$", message = "Invalid telegram format")
    private String telegram;

    private Boolean hasTelegram;

}
