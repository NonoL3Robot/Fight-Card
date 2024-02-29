package fr.dawan.business.user;

import fr.dawan.generic.GenericService;
import fr.dawan.auth.UserSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserService extends UserDetailsService, GenericService<UserDto> {
    
    @Override
    UserSecurity loadUserByUsername(String username) throws UsernameNotFoundException;
}
