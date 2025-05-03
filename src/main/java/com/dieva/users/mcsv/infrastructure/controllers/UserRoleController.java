package com.dieva.users.mcsv.infrastructure.controllers;

import com.dieva.users.mcsv.domain.model.Roles;
import com.dieva.users.mcsv.domain.model.User;
import com.dieva.users.mcsv.domain.port.in.UserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping("/user-role/{user}")
    public ResponseEntity<User> createUserRole(@PathVariable Long user, @RequestBody Roles role) {
        return ResponseEntity.created(URI.create("/user-role/" + user))
                .body(userRoleService.createUserRole(user, role));
    }
}
