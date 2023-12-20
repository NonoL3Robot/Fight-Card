package fr.dawan.business.licence;

import fr.dawan.business.generic.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("licences")
public class LicenceController extends GenericController<LicenceDto, LicenceService> {
    public LicenceController(LicenceService service) {
        super(service);
    }


}
