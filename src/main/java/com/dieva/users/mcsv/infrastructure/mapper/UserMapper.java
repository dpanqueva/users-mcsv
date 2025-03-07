package com.dieva.users.mcsv.infrastructure.mapper;

import com.dieva.users.mcsv.domain.model.User;
import com.dieva.users.mcsv.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<UserEntity, User> {

    @Override
    User toEntity(UserEntity dto);

    //@Mapping(target = "id", ignore = true)
    @Override
    UserEntity toDto(User entity);

    @Override
    List<User> toDto(List<UserEntity> dtoList);

    @Override
    List<UserEntity> toEntity(List<User> entityList);

    @Override
    default Page<UserEntity> toDto(Page<User> entityPage) {
        return GenericMapper.super.toDto(entityPage);
    }

    @Override
    Set<UserEntity> toDto(Set<User> entityList);
}
