package ru.project.students.dto.student.field;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.validation.Valid;

@Data
@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contact {
    @Valid
    @Embedded
    private Phone fPhone;
    @Valid
    @Embedded
    private Telegram fTelegram;
    @Valid
    @Embedded
    private Email fEmail;

    private Boolean hasContact;
}
