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

    @Embedded
    private Contact fContact;
    @Embedded
    private Git fGit;
    @Embedded
    private PersonalData personalData;
    @Transient
    private String test;

    @PostLoad
    public void init(){
        test = "test";
    }
}
