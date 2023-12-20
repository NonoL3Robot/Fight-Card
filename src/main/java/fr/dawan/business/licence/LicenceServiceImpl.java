package fr.dawan.business.licence;

import fr.dawan.business.generic.GenericServiceImpl;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LicenceServiceImpl extends GenericServiceImpl<Licence, LicenceRepository, LicenceDto, LicenceMapper> implements LicenceService {
    public LicenceServiceImpl(LicenceRepository repository, LicenceMapper mapper) {
        super(repository, mapper);
    }

}
