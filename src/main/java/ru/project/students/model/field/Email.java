package ru.project.students.model.field;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@Embeddable
public class Email {
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email format")
    @Column(name = "email")
    private String email;

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
}
