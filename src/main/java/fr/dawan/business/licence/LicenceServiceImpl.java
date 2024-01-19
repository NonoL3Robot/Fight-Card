package fr.dawan.business.licence;

import fr.dawan.business.generic.GenericServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class LicenceServiceImpl extends GenericServiceImpl<Licence, LicenceRepository, LicenceDto, LicenceMapper> implements LicenceService {
    public LicenceServiceImpl(LicenceRepository repository, LicenceMapper mapper) {
        super(repository, mapper);
    }

}
