package com.dieva.users.mcsv.domain.port.in;

import com.dieva.users.mcsv.domain.model.Roles;
import com.dieva.users.mcsv.domain.model.User;

public interface UserRoleService {

    User createUserRole(Long user,Roles roles);
}
