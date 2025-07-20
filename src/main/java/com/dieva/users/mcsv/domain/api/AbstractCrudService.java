package com.dieva.users.mcsv.domain.api;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Slf4j
public abstract class AbstractCrudService<
        E ,
        R extends JpaRepository<E, Long>,
        D, // DTO type
        P // Projection type
        > {

    private static final String PREFIX = "message.";
    private static final String CREADO = PREFIX + "default.created.resource";
    private static final String ELIMINADO = PREFIX + "default.deleted.resource";
    private static final String ERROR = PREFIX + "default.error";
    private static final String ACTUALIZADO = PREFIX + "default.updated.resource";
    private static final String NO_ENCONTRADO = PREFIX + "default.not.found.resource";

    protected final R daoRepository;

    protected AbstractCrudService(final R daoRepository) {
        this.daoRepository = daoRepository;
    }

    protected D create(final D dto) {
        return Optional.ofNullable(dto)
                .map(this::mapToEntity)
                .map(daoRepository::saveAndFlush)
                .map(entity -> {
                    log.info(CREADO, entity);
                    return dto;
                })
                .orElseThrow(() -> new IllegalStateException(
                        ERROR));
    }

    protected Page<D> getDtoPageable(final Long id, final Pageable pageable) {
        final Page<P> results = findAll(id,pageable);
        return new PageImpl<>(results.stream()
                .map(this::mapToDto)
                .toList(),
                pageable,
                results.getTotalElements());
    }

    public D update(final Long id, final String acceptLanguage, final D dto) {
        return findById(id)
                .map(projection -> mapProjectionEntityUpdate(projection, id, dto, acceptLanguage))
                .map(daoRepository::saveAndFlush)
                .map(entity -> {
                    log.info(ACTUALIZADO, entity);
                    return dto;
                })
                .orElseThrow(() -> new IllegalStateException(ERROR));
    }

    public D getDtoById(final Long id) {
        return findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new IllegalArgumentException(NO_ENCONTRADO));
    }

    public void delete(final Long id) {
        daoRepository.deleteById(id);
        log.info(ELIMINADO, id);
    }

    protected abstract Optional<P> findById(Long id);

    /** projection to dto */
    protected abstract D mapToDto(P projection);

    /** dto to entity */
    protected abstract E mapToEntity(D dto);

    /** projection to entity */
    protected abstract E projectionEntity(P projection);

    protected abstract E mapProjectionEntityUpdate(P projection, Long id, D dto, String acceptLanguage);

    protected abstract Page<P> findAll (Long id,Pageable pageable);

}
