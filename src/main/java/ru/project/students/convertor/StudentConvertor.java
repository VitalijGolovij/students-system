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

        studentDto.setId(student.getId());

        PersonalData personalData = modelMapper.map(student, PersonalData.class);
        studentDto.setPersonalData(personalData);

        Git git = modelMapper.map(student, Git.class);
        studentDto.setFGit(git);

        Contact contact = new Contact();
        contact.setHasContact(false);

        if (student.getPhone() != null || student.getEmail() != null || student.getTelegram() != null){
            contact.setHasContact(true);
            contact.setFPhone(modelMapper.map(student, Phone.class));
            contact.setFEmail(modelMapper.map(student, Email.class));
            contact.setFTelegram(modelMapper.map(student, Telegram.class));
        }
        studentDto.setFContact(contact);
        return studentDto;
    }
}
