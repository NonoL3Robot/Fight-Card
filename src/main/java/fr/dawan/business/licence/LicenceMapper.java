package fr.dawan.business.licence;

import fr.dawan.business.carte.Carte;
import fr.dawan.business.generic.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LicenceMapper extends GenericMapper<Licence, LicenceDto> {

    @Override
    @Mapping(source = "cartes", target = "carteCount", qualifiedByName = "carteConverter")
    LicenceDto toDto(Licence entity);

    @Named("carteConverter")
    default long cartesToCarteCount(List<Carte> cartes) {
        if (cartes == null) return 0;
        return cartes.size();
    }
}
