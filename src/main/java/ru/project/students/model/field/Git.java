package ru.project.students.model.field;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

@Data
@Embeddable
@NoArgsConstructor
public class Git {
    private Boolean hasGit;
    @Pattern(regexp = "^https:\\/\\/\\S+\\/\\S+$", message = "Invalid git format")
    @Column(name = "git")
    private String git;

    public void setGit(String git) {
        if (git != null) {
            this.git = git;
            this.hasGit = true;
        }
    }

}
