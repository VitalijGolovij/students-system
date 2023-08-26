package ru.project.students.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;

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

    public Boolean hasContact(){
        return (telegram != null && phone != null && email != null);
    }

    public Boolean hasGit(){
        return (git != null);
    }
}
