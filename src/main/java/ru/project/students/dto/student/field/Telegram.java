package ru.project.students.dto.student.field;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class Telegram {
    @Pattern(regexp = "^@[\\w\\d_]+$", message = "Invalid telegram format")
    private String telegram;

    private Boolean hasTelegram;

    public Telegram(String telegram) {
        if (telegram != null) {
            setTelegram(telegram);
        }
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
        this.hasTelegram = true;
    }
}
