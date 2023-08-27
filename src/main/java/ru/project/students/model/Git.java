package ru.project.students.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
@NoArgsConstructor
public class Git {
    private Boolean hasGit;

    @Column(name = "git")
    private String git;

    public void setGit(String git) {
        if (git != null) {
            this.git = git;
            this.hasGit = true;
        }
    }
}
