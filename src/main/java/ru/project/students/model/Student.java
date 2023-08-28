package ru.project.students.model;

import lombok.Data;

import javax.persistence.*;
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
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "telegram")
    private String telegram;
    @Column(name = "git")
    private String git;
    @Column(name = "autoexam")
    private String autoexam;
    @Column(name = "group_id")
    private Integer groupId;

    @Transient
    private String lastnameInitials;
    @Transient
    private String contact;

    @PostLoad
    public void setTransientFields(){
        setLastnameInitials();
        setContact();
    }
    private void setLastnameInitials(){
        lastnameInitials = lastname + " " + firstname.charAt(0)
                + " " + (patronymic == null ? "" : patronymic.charAt(0));
    }
    private void setContact(){
        contact = (phone == null) ? null : phone;
        contact = (telegram == null) ? contact : telegram;
        contact = (email == null) ? contact : email;
    }
}
