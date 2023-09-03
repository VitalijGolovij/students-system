package ru.project.students.convertor;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.project.students.dto.student.StudentDto;
import ru.project.students.dto.student.field.*;
import ru.project.students.model.Student;

@Component
@RequiredArgsConstructor
public class StudentConvertor {
    private final ModelMapper modelMapper;

    public Student toStudent(StudentDto studentDto){
        if (studentDto == null)
            return null;

        Student student = new Student();

        if (studentDto.getPersonalData() != null){
            modelMapper.map(studentDto.getPersonalData(), student);
        }
        if (studentDto.getFGit() != null){
            modelMapper.map(studentDto.getFGit(), student);
        }
        if (studentDto.getFContact() != null){
            Contact contact = studentDto.getFContact();
            modelMapper.map(contact, student);

            if (contact.getFEmail() != null)
                modelMapper.map(contact.getFEmail(), student);
            if (contact.getFTelegram() != null)
                modelMapper.map(contact.getFTelegram(), student);
            if (contact.getFPhone() != null)
                modelMapper.map(contact.getFPhone(), student);
        }
        return student;
    }

    public StudentDto toStudentDto(Student student){
        if (student == null)
            return null;

        StudentDto studentDto = new StudentDto();

        PersonalData personalData = modelMapper.map(student, PersonalData.class);
        studentDto.setPersonalData(personalData);

        if (student.getHasGit()){
            Git git = modelMapper.map(student, Git.class);
            studentDto.setFGit(git);
        }
        if (student.getHasContact()){
            Contact contact = new Contact();

            contact.setFPhone(student.getHasPhone() ? modelMapper.map(student, Phone.class) : null);
            contact.setFEmail(student.getHasEmail() ? modelMapper.map(student, Email.class) : null);
            contact.setFTelegram(student.getHasTelegram() ? modelMapper.map(student, Telegram.class) : null);
            studentDto.setFContact(contact);
        }
        return studentDto;
    }
}
