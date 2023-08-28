package ru.project.students.model.field;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
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

    @Transient
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

    @PostLoad
    public void postInit(){
        if (fPhone != null || fEmail != null || fTelegram != null){
            this.hasContact = true;
        } else {
            this.hasContact = false;
        }

        if (fPhone != null){
            fPhone.setHasPhone(true);
        }
        if (fEmail != null){
            fEmail.setHasEmail(true);
        }
        if (fTelegram != null){
            fTelegram.setHasTelegram(true);
        }
    }
}
