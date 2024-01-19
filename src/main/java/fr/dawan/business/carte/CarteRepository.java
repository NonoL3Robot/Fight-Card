package fr.dawan.business.carte;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteRepository extends JpaRepository<Carte, Long> {
    Page<Carte> findByNameLike(String name, Pageable pageable);

    Page<Carte> findByLicence_NameLike(String name, Pageable pageable);

    Page<Carte> findByLicence_Id(long id, Pageable pageable);


}
