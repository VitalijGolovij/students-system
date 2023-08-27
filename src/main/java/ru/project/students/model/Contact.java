package ru.project.students.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Data
public class Contact {
    @Embedded
    private Phone fPhone;
    @Embedded
    private Telegram fTelegram;
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
