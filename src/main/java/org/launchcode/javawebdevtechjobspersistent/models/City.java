package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class City extends AbstractEntity{

    @OneToMany(mappedBy = "city")
    private final List<Job> jobs=new ArrayList<>();

    public City() {
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
