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
public class Git {
    private Boolean hasGit;

    @Pattern(regexp = "^https:\\/\\/\\S+\\/\\S+$", message = "Invalid git format")
    private String git;

}
