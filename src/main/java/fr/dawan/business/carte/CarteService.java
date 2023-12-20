package fr.dawan.business.carte;

import fr.dawan.business.generic.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarteService extends GenericService<CarteDto> {
    Page<CarteDto> findByName(String name, Pageable pageable);

    Page<CarteDto> findByLicence_NameLike(String s, Pageable pageable);
}
