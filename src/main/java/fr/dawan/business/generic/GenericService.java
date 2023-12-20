package fr.dawan.business.generic;

import fr.dawan.business.licence.LicenceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GenericService<D> {
    Page<D> findAll(Pageable pageable); // Tous les enregistrements avec pagination
    Optional<D> findById(long id);
    D saveOrUpdate(D dto);
    void deleteById(long id);


}
