package com.dieva.users.mcsv.domain.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
public class Roles {
    private Long id;

    private String roleName;

    private String description;
}
