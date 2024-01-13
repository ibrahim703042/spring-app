package com.kwizera.ibrahim.adminPanel.repository;

import com.kwizera.ibrahim.adminPanel.entity.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City,Long> {
}
