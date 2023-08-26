package ru.project.students.dto.student.field;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class Email {
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email format")
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
