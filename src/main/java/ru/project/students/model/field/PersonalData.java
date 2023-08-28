package ru.project.students.model.field;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

@Data
@Embeddable
@NoArgsConstructor
public class PersonalData {
    @Pattern(regexp = "^[А-ЯЁа-яё]+$", message = "Invalid firstname format")
    @Column(name = "first_name")
    private String firstname;

    @Pattern(regexp = "^[А-ЯЁа-яё]+$", message = "Invalid lastname format")
    @Column(name = "last_name")
    private String lastname;

    @Pattern(regexp = "^[А-ЯЁа-яё]+$", message = "Invalid patronymic format")
    @Column(name = "patronymic")
    private String patronymic;

    @Transient
    private String lastnameInitials;

    public PersonalData(String firstname, String lastname, String patronymic){
        this.firstname = firstname;
        this.lastname = lastname;
        this.patronymic = patronymic;

        this.lastnameInitials = lastname + " " + firstname.charAt(0) + " " + patronymic.charAt(0);
    }

    @PostLoad
    public void postInit(){
        this.lastnameInitials = lastname + " " + firstname.charAt(0) + " " + patronymic.charAt(0);
    }
}
