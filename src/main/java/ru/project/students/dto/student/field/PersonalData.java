package ru.project.students.dto.student.field;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

@Data
@Embeddable
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonalData {
    @Pattern(regexp = "^[А-ЯЁа-яё]+$", message = "Invalid firstname format")
    private String firstname;

    @Pattern(regexp = "^[А-ЯЁа-яё]+$", message = "Invalid lastname format")
    private String lastname;

    @Pattern(regexp = "^[А-ЯЁа-яё]+$", message = "Invalid patronymic format")
    private String patronymic;

    private String lastnameInitials;

}
