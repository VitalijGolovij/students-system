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
    @Column(name = "lastname_initials", insertable = false, updatable = false)
    private String lastnameInitials;
    @Column(name = "contact", insertable = false, updatable = false)
    private String contact;
    @Column(name = "has_contact", insertable = false, updatable = false)
    private Boolean hasContact;
    @Column(name = "has_phone", insertable = false, updatable = false)
    private Boolean hasPhone;
    @Column(name = "has_telegram", insertable = false, updatable = false)
    private Boolean hasTelegram;
    @Column(name = "has_email", insertable = false, updatable = false)
    private Boolean hasEmail;
    @Column(name = "has_git", insertable = false, updatable = false)
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
