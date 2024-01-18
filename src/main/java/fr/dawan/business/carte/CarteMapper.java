package fr.dawan.business.carte;

import fr.dawan.business.generic.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarteMapper extends GenericMapper<Carte, CarteDto> {
    @Override
    @Mapping(source = "licence.name", target = "licenceName")
    @Mapping(source = "licence.id", target = "licenceId")
    CarteDto toDto(Carte entity);
}
