package fr.dawan.security.auth;

import fr.dawan.business.user.UserMapper;
import fr.dawan.business.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public LoginResponseDto authenticate(LoginDto credentials) {
        // Ceci vérifie la correspondance des credentials avec un utilisateur dans la BDD
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword(), new ArrayList<>()));
        // Une fois la vérification passée, on peut renvoyer une réponse contenant le token
        UserSecurity user = (UserSecurity) userDetailsService.loadUserByUsername(credentials.getEmail());
        return new LoginResponseDto(user);
    }
    
    public void register(RegisterDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(userMapper.toEntity(dto).setActive(true));
        // Il est possible (plutôt que d'activer directement le compte) de lancer une méthode (envoi de mail)
        // qui permettra d'activer le compte APRES une confirmation
    }
}
