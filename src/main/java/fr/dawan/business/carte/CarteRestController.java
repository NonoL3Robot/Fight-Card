package fr.dawan.business.carte;

import fr.dawan.generic.AbstractGenericRestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/cartes")
public class CarteRestController extends AbstractGenericRestController<CarteDto, CarteService> {
    public CarteRestController(CarteService service) {
        super(service);
    }
}
