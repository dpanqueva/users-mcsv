package com.dieva.users.mcsv.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String username;
    private String email;
    private String passwordHash;
    private List<Roles> roles;
    private Boolean isEnabled;
}
