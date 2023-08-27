package ru.project.students.model.field;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@Embeddable
public class Phone {
    @Pattern(regexp = "^8\\d{10}$", message = "Invalid phone format")
    @Column(name = "phone")
    private String phone;

    private Boolean hasPhone;

    public Phone(String phone){
        if (phone != null) {
            setPhone(phone);
        }
    }

    public void setPhone(String phone) {
        this.phone = phone;
        this.hasPhone = true;
    }
}
