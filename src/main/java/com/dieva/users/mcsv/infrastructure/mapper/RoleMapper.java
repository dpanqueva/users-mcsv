package com.dieva.users.mcsv.infrastructure.mapper;

import com.dieva.users.mcsv.domain.model.Roles;
import com.dieva.users.mcsv.infrastructure.entities.RoleEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper extends GenericMapper<RoleEntity, Roles>{
    @Override
    Roles toEntity(RoleEntity dto);

    @Override
    RoleEntity toDto(Roles entity);

    @Override
    List<Roles> toDto(List<RoleEntity> dtoList);

    @Override
    List<RoleEntity> toEntity(List<Roles> entityList);

    @Override
    default Page<RoleEntity> toDto(Page<Roles> entityPage) {
        return GenericMapper.super.toDto(entityPage);
    }

    @Override
    Set<RoleEntity> toDto(Set<Roles> entityList);
}
