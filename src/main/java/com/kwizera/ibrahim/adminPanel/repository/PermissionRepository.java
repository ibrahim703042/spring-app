package com.kwizera.ibrahim.adminPanel.repository;

import com.kwizera.ibrahim.adminPanel.entity.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends CrudRepository<Permission,Long> {
}
