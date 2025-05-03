package com.dieva.users.mcsv.application.service;

import com.dieva.users.mcsv.domain.model.Roles;
import com.dieva.users.mcsv.domain.model.User;
import com.dieva.users.mcsv.domain.port.in.UserRoleRepository;
import com.dieva.users.mcsv.domain.port.in.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public User createUserRole(Long user, Roles roles) {
        return userRoleRepository.createUserRole(user,roles);
    }
}
