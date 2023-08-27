package ru.project.students.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Embeddable
@NoArgsConstructor
public class Email {
    @Column(name = "email")
    private String email;
    @Transient
    private Boolean hasEmail;

    public Email(String email){
        if (email != null){
            setEmail(email);
        }
    }

    public void setEmail(String email) {
        this.email = email;
        this.hasEmail = true;
    }

    @PostLoad
    public void initHasEmail(){
        if (email != null){
            this.hasEmail = true;
        } else {
            this.hasEmail = false;
        }
    }
}
