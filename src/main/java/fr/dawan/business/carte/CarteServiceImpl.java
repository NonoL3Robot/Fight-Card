package fr.dawan.business.carte;

import fr.dawan.business.generic.GenericServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarteServiceImpl extends GenericServiceImpl<Carte, CarteRepository, CarteDto, CarteMapper> implements CarteService {
    public CarteServiceImpl(CarteRepository repository, CarteMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public Page<CarteDto> findByName(String name, Pageable pageable) {
        return repository.findByNameLike("%" + name + "%", pageable).map(mapper::toDto);
    }

    public Page<CarteDto> findByLicence_NameLike(String name, Pageable pageable) {
        return repository.findByLicence_NameLike("%" + name + "%", pageable).map(mapper::toDto);
    }
}
