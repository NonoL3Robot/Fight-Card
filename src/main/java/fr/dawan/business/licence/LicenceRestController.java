package fr.dawan.business.licence;

import fr.dawan.generic.AbstractGenericRestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/v1/licences")
public class LicenceRestController extends AbstractGenericRestController<LicenceDto, LicenceService> {
    public LicenceRestController(LicenceService service) {
        super(service);
    }
}
