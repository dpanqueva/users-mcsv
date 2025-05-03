package com.dieva.users.mcsv.infrastructure.repository;

import com.dieva.users.mcsv.infrastructure.entities.RoleEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRoleRepositoryPersistence extends CrudRepository<RoleEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_roles (user_id, role_id) VALUES (:userId, :roleId)", nativeQuery = true)
    void insertUserRole(Long userId, Long roleId);
}

