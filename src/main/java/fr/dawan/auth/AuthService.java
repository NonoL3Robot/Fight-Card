package fr.dawan.auth;

import fr.dawan.business.user.UserMapper;
import fr.dawan.business.user.UserRepository;
import fr.dawan.auth.dtos.LoginDto;
import fr.dawan.auth.dtos.LoginResponseDto;
import fr.dawan.auth.dtos.RegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface AuthService {
    
    void register(RegisterDto register);
    LoginResponseDto authenticate(LoginDto login) throws Exception;
}
