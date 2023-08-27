package ru.project.students.model.field;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.Valid;

@Data
@Embeddable
public class Contact {
    @Valid
    @Embedded
    private Phone fPhone;
    @Valid
    @Embedded
    private Telegram fTelegram;
    @Valid
    @Embedded
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
