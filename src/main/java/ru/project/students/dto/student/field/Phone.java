package ru.project.students.dto.student.field;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@Embeddable
public class Phone {
    @Pattern(regexp = "^8\\d{10}$", message = "Invalid phone format")
    private String phone;
    private Boolean hasPhone;
}
