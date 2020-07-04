package org.launchcode.javawebdevtechjobspersistent.models.data;

import org.launchcode.javawebdevtechjobspersistent.models.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {
}
