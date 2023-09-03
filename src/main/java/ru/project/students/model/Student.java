package ru.project.students.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "students")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "phone")
    private String phone;
    @Column(name = "telegram")
    private String telegram;
    @Column(name = "email")
    private String email;
    @Column(name = "git")
    private String git;
    @Column(name = "lastname_initials")
    private String lastnameInitials;
    @Column(name = "contact")
    private String contact;
    @Column(name = "has_contact")
    private Boolean hasContact;
    @Column(name = "has_phone")
    private Boolean hasPhone;
    @Column(name = "has_telegram")
    private Boolean hasTelegram;
    @Column(name = "has_email")
    private Boolean hasEmail;
    @Column(name = "has_git")
    private Boolean hasGit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
