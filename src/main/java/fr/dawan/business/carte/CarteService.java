package fr.dawan.business.carte;

import fr.dawan.business.generic.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface CarteService extends GenericService<CarteDto> {
    Page<CarteDto> findByName(String name, Pageable pageable);


    Page<CarteDto> findByLicence_Id(long id, Pageable pageable);

    Page<CarteDto> getById(long id, Pageable pageable);
}
