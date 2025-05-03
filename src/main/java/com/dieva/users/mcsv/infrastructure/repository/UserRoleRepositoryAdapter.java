package com.dieva.users.mcsv.infrastructure.repository;

import com.dieva.users.mcsv.domain.model.Roles;
import com.dieva.users.mcsv.domain.model.User;
import com.dieva.users.mcsv.domain.port.in.UserRoleRepository;
import com.dieva.users.mcsv.infrastructure.entities.RoleEntity;
import com.dieva.users.mcsv.infrastructure.entities.UserEntity;
import com.dieva.users.mcsv.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRoleRepositoryAdapter implements UserRoleRepository {

    private final UserRoleRepositoryPersistence userRoleRepository;
    private final UserRepositoryPersistence userRepository;
    private final UserMapper userMapper;

    public UserRoleRepositoryAdapter(UserRoleRepositoryPersistence userRoleRepository, UserRepositoryPersistence userRepository, UserMapper userMapper) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User createUserRole(Long user, Roles roles) {
        Optional<RoleEntity> roleEntity = userRoleRepository.findById(roles.getId());
        Optional<UserEntity> userEntity = userRepository.findById(user);
        if (roleEntity.isPresent() && userEntity.isPresent()) {
            userRoleRepository.insertUserRole(userEntity.get().getId(), roleEntity.get().getId());
            userEntity.get().setRoles(List.of(roleEntity.get()));
        }
        return userMapper.toEntity(userEntity.get());
    }
}
