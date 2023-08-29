package ru.project.students.model.field;

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
public class Git {
    @Transient
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
    @PostLoad
    private void postInit(){
        if (git != null){
            hasGit = true;
        } else {
            hasGit = false;
        }
    }
}
