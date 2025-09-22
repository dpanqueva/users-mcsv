package com.dieva.users.mcsv.infrastructure.repository;

import com.dieva.users.mcsv.domain.model.User;
import com.dieva.users.mcsv.domain.port.in.UserRepository;
import com.dieva.users.mcsv.infrastructure.entities.UserEntity;
import com.dieva.users.mcsv.infrastructure.mapper.RoleMapper;
import com.dieva.users.mcsv.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final UserRepositoryPersistence userRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    public UserRepositoryAdapter(UserRepositoryPersistence userRepository, UserMapper userMapper,
                                 RoleMapper roleMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    @Transactional
    public User save(User user) {
        UserEntity userEntity = userMapper.toDto(user);
        return userMapper.toEntity(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public User update(User user, Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            UserEntity userEntityUpdated = userEntity.get();
            userEntityUpdated.setUsername(user.getUsername());
            userEntityUpdated.setEmail(user.getEmail());
            userEntityUpdated.setPasswordHash(user.getPasswordHash());
            userEntityUpdated.setRoles(roleMapper.toEntity(user.getRoles()));
            return userMapper.toEntity(userRepository.save(userEntityUpdated));
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.map(userMapper::toEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        List<UserEntity> userEntities = (List<UserEntity>) userRepository.findAll();
        return userMapper.toDto(userEntities);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username).map(userMapper::toEntity);
    }
}
