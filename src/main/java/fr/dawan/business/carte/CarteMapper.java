package fr.dawan.business.carte;

import fr.dawan.generic.GenericMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CarteMapper extends GenericMapper<CarteDto, Carte> {
    
    @Override
    @Mapping(source = "licence.id", target = "licenceId")
    CarteDto toDto(Carte entity);
    
    @Override
    @InheritInverseConfiguration
    Carte toEntity(CarteDto dto);
}
