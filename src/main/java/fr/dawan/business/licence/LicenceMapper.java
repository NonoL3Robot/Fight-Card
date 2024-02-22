package fr.dawan.business.licence;

import fr.dawan.business.carte.CarteMapper;
import fr.dawan.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(uses = CarteMapper.class)
public interface LicenceMapper extends GenericMapper<LicenceDto, Licence> {

}
