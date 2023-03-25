package com.fahad.microservice.mapper;

import java.util.List;
import java.util.Optional;

public interface ObjectMapper<E, D> {

    Optional<E> copyDTOToEntity(Optional<D> dto, Class<E> destinationObject);

    Optional<D> copyEntityToDTO(Optional<E> entity, Class<D> destinationObject);

    List<E> mapAllToEntityList(List<D> dtoList, Class<E> destinationObject);

    List<D> mapAllToDTOList(List<E> entityList, Class<D> destinationObject);
}
