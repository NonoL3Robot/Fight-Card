package fr.dawan.business.carte;

import fr.dawan.business.generic.GenericController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cartes")
public class CarteController extends GenericController<CarteDto, CarteService> {
    public CarteController(CarteService service) {
        super(service);
    }

    @GetMapping("byName/{name}")
    public Page<CarteDto> findByName(@PathVariable String name, Pageable pageable) {
        return service.findByName(name, pageable);
    }

    @GetMapping("byLicence/{name}")
    public Page<CarteDto> findByLicenceNameLike(@PathVariable String name, Pageable pageable) {
        return service.findByLicence_NameLike(name, pageable);
    }
}
