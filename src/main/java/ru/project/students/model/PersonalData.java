package ru.project.students.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
@NoArgsConstructor
public class PersonalData {
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "patronymic")
    private String patronymic;

    private String lastnameInitials;

    public PersonalData(String firstname, String lastname, String patronymic){
        this.firstname = firstname;
        this.lastname = lastname;
        this.patronymic = patronymic;

        this.lastnameInitials = lastname + " " + firstname.charAt(0) + " " + patronymic.charAt(0);
    }
}
