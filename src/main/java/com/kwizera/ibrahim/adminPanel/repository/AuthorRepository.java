package com.kwizera.ibrahim.adminPanel.repository;

import com.kwizera.ibrahim.adminPanel.entity.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author,Long> {
}
