package com.kwizera.ibrahim.adminPanel.repository;

import com.kwizera.ibrahim.adminPanel.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
}
