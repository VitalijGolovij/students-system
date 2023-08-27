package ru.project.students.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@Embeddable
public class Telegram {
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
