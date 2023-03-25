package com.fahad.microservice.mapper;

import com.fahad.microservice.exception.GenericException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ObjectMapperImpl<E, D> implements ObjectMapper<E, D> {

    @Override
    public Optional<E> copyDTOToEntity(Optional<D> dto, Class<E> destinationObject) {

        if (dto.isPresent()) {
            E entity = getNewInstanceOfEntity(destinationObject);
            BeanUtils.copyProperties(dto.get(), entity);
            return Optional.ofNullable(entity);
        }
        return Optional.empty();
    }

    @Override
    public Optional<D> copyEntityToDTO(Optional<E> entity, Class<D> destinationObject) {

        if (entity.isPresent()) {
            D dto = getNewInstanceOfDTO(destinationObject);
            BeanUtils.copyProperties(entity.get(), dto);
            return Optional.ofNullable(dto);
        }
        return Optional.empty();
    }

    @Override
    public List<E> mapAllToEntityList(List<D> dtoList, Class<E> destinationObject) {

        if (dtoList != null && !dtoList.isEmpty()) {
            return dtoList.stream()
                    .map(dto -> copyDTOToEntity(Optional.ofNullable(dto), destinationObject).get())
                    .collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<D> mapAllToDTOList(List<E> entityList, Class<D> destinationObject) {

        if (entityList != null && !entityList.isEmpty()) {
            return entityList.stream()
                    .map(entity -> copyEntityToDTO(Optional.ofNullable(entity), destinationObject).get())
                    .collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }

    private E getNewInstanceOfEntity(Class<E> destinationObject) {
        try {
            return destinationObject.newInstance();
        } catch (InstantiationException e) {
            throw new GenericException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new GenericException(e.getMessage());
        }
    }

    private D getNewInstanceOfDTO(Class<D> destinationObject) {
        try {
            return destinationObject.newInstance();
        } catch (InstantiationException e) {
            throw new GenericException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new GenericException(e.getMessage());
        }
    }
}
