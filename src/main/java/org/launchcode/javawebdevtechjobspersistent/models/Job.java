package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Job extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Employer is required.")
    private Employer employer;

    @ManyToMany
    private final List<Skill> skills= new ArrayList<>();

    public Job() {
    }

    public Job(Employer anEmployer) {
        this.employer = anEmployer;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> aSkills){
        for(Skill skill : aSkills){
            this.skills.add(skill);
        }
    }

}
