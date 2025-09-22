package com.dieva.users.mcsv.infrastructure.repository;

import com.dieva.users.mcsv.infrastructure.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepositoryPersistence extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
}
