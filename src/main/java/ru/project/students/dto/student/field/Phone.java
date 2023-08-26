package ru.project.students.dto.student.field;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class Phone {
    @Pattern(regexp = "^8\\d{10}$", message = "Invalid phone format")
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
