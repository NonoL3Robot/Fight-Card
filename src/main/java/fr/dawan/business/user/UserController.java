package fr.dawan.business.user;

import fr.dawan.generic.AbstractGenericRestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController extends AbstractGenericRestController<UserDto, UserService> {
    public UserController(UserService service) {
        super(service);
    }
    
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public void deleteById(long id) {
        super.deleteById(id);
    }
}

