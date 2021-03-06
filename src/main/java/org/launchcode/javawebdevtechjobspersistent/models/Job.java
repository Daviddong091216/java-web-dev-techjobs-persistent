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

    @ManyToOne
    @NotNull(message = "City is required.")
    private City city;

    @ManyToMany
    private final List<Skill> skills = new ArrayList<>();

    public Job() {
    }

    public Job(Employer employer, City city) {
        this.employer = employer;
        this.city = city;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> aSkills) {
        for (Skill skill : aSkills) {
            this.skills.add(skill);
        }
    }

}
