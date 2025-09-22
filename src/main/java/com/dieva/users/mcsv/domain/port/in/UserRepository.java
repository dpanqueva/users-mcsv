package com.dieva.users.mcsv.domain.port.in;

import com.dieva.users.mcsv.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    User update(User user, Long id);
    Optional<User> findById(Long id);
    List<User> findAll();
    void deleteById(Long id);

    Optional<User> findByUsername(String username);
}
