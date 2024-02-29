package fr.dawan.business.licence;

import fr.dawan.generic.AbstractGenericService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class LicenceServiceImpl extends AbstractGenericService<Licence, LicenceDto, LicenceRepository, LicenceMapper> implements LicenceService {
    public LicenceServiceImpl(LicenceRepository repository, LicenceMapper mapper, ApplicationEventPublisher publisher) {
        super(repository, mapper, publisher);
    }
}
