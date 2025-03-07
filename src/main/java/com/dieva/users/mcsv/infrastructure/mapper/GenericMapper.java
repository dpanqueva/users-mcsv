package com.dieva.users.mcsv.infrastructure.mapper;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface GenericMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toDto(List<D> dtoList);

    List<D> toEntity(List<E> entityList);

    default Page<D> toDto(Page<E> entityPage) {
        return entityPage.map(data -> toDto(data));
    }

    Set<D> toDto(Set<E> entityList);
}
