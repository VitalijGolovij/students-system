package ru.project.students.convertor;

import org.springframework.stereotype.Component;
import ru.project.students.dto.student.StudentDto;
import ru.project.students.dto.student.field.*;
import ru.project.students.model.Student;

@Component
public class StudentConvertorImpl implements StudentConvertor{
    @Override
    public Student toStudent(StudentDto studentDto) {
        Student student = new Student();

        Contact contact = studentDto.getFContact();
        Git git = studentDto.getFGit();
        PersonalData personalData = studentDto.getPersonalData();

        student.setFirstname(personalData.getFirstname());
        student.setLastname(personalData.getLastname());
        student.setPatronymic(personalData.getPatronymic());

        if (contact != null && contact.getHasContact()){
            student.setPhone(contact.getFPhone() != null ? contact.getFPhone().getPhone() : null);
            student.setEmail(contact.getFEmail() != null ? contact.getFEmail().getEmail() : null);
            student.setTelegram(contact.getFTelegram() != null ? contact.getFTelegram().getTelegram() : null);
        }

        if (git != null && git.getHasGit()){
            student.setGit(git.getGit());
        }

        return student;
    }

    @Override
    public StudentDto toStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();

        PersonalData personalData = new PersonalData(student.getFirstname(),
                                                    student.getLastname(),
                                                    student.getPatronymic());
        studentDto.setPersonalData(personalData);

        if (student.hasGit()){
            Git git = new Git();
            git.setGit(student.getGit());
            studentDto.setFGit(git);
        }

        if (student.hasContact()){
            Contact contact = new Contact();

            contact.setHasContact(true);
            contact.setFPhone(student.getPhone()!=null? new Phone(student.getPhone()):null);
            contact.setFEmail(student.getEmail()!=null? new Email(student.getEmail()):null);
            contact.setFTelegram(student.getTelegram()!=null? new Telegram(student.getTelegram()):null);

            studentDto.setFContact(contact);
        }

        return studentDto;
    }
}
