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
public class Contact {
    @Valid
    @Embedded
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Phone fPhone;
    @Valid
    @Embedded
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Telegram fTelegram;
    @Valid
    @Embedded
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Email fEmail;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean hasContact;
}
