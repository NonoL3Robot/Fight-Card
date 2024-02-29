package fr.dawan.business.user;

import fr.dawan.generic.AbstractGenericService;
import fr.dawan.auth.UserSecurity;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl extends AbstractGenericService<User, UserDto, UserRepository, UserMapper> implements UserService {
    public UserServiceImpl(UserRepository repository, UserMapper mapper, ApplicationEventPublisher publisher) {
        super(repository, mapper, publisher);
    }
    
    @Override
    public UserSecurity loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).map(UserSecurity::new).orElseThrow();
    }
}
