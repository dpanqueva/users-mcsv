package com.dieva.users.mcsv.domain.port.in;

import com.dieva.users.mcsv.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    User updateUser(User user, Long id);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);
}
