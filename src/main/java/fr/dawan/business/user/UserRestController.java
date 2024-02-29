package fr.dawan.business.user;

import fr.dawan.generic.AbstractGenericRestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/v1/users")
public class UserRestController extends AbstractGenericRestController<UserDto, UserService> {
    public UserRestController(UserService service) {
        super(service);
    }
}
