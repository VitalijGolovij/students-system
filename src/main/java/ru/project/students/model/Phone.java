package ru.project.students.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
@NoArgsConstructor
public class Phone {
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
