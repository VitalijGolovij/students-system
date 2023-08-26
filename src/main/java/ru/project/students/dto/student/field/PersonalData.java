package ru.project.students.dto.student.field;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

@Data
@Embeddable
@NoArgsConstructor
public class PersonalData {
    @Pattern(regexp = "^[А-ЯЁа-яё]+$", message = "Invalid firstname format")
    private String firstname;

    @Pattern(regexp = "^[А-ЯЁа-яё]+$", message = "Invalid lastname format")
    private String lastname;

    @Pattern(regexp = "^[А-ЯЁа-яё]+$", message = "Invalid patronymic format")
    private String patronymic;

    private String lastnameInitials;

    public PersonalData(String firstname, String lastname, String patronymic){
        this.firstname = firstname;
        this.lastname = lastname;
        this.patronymic = patronymic;

        this.lastnameInitials = lastname + " " + firstname.charAt(0) + " " + patronymic.charAt(0);
    }
}
