package fr.dawan.business.user;

import fr.dawan.business.generic.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController extends GenericController<UserDto, UserService> {
    public UserController(UserService service) {
        super(service);
    }


}
