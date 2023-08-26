package ru.project.students.dto.student.field;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Data
@Embeddable
public class Contact {
    @Valid
    private Phone fPhone;
    @Valid
    private Telegram fTelegram;
    @Valid
    private Email fEmail;

    private Boolean hasContact;

    public void setFPhone(Phone fPhone){
        this.fPhone = fPhone;
        this.hasContact = true;
    }

    public void setFEmail(Email fEmail) {
        this.fEmail = fEmail;
        this.hasContact = true;
    }

    public void setFTelegram(Telegram fTelegram) {
        this.fTelegram = fTelegram;
        this.hasContact = true;
    }
}
