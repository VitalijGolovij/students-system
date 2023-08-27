package ru.project.students.model.field;

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
    @Column(name = "telegram")
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
