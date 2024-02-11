package fr.dawan.generic;

import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface GenericMapper<D, E> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    D toDto(E entity);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    E toEntity(D dto);
}
