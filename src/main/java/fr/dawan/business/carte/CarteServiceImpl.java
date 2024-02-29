package fr.dawan.business.carte;

import fr.dawan.generic.AbstractGenericService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


@Service
public class CarteServiceImpl extends AbstractGenericService<Carte, CarteDto, CarteRepository, CarteMapper> implements CarteService {
    public CarteServiceImpl(CarteRepository repository, CarteMapper mapper, ApplicationEventPublisher publisher) {
        super(repository, mapper, publisher);
    }
}
