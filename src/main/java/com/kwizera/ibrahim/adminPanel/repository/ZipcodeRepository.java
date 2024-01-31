package com.kwizera.ibrahim.adminPanel.repository;

import com.kwizera.ibrahim.adminPanel.entity.Zipcode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipcodeRepository extends CrudRepository<Zipcode,Long> {
}
