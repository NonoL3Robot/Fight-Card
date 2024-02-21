package fr.dawan.business.carte;

import fr.dawan.generic.AbstractGenericRestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/v1/cartes")
public class CarteRestController extends AbstractGenericRestController<CarteDto, CarteService> {
    public CarteRestController(CarteService service) {
        super(service);
    }
}
