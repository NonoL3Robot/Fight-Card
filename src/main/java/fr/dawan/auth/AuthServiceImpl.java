package fr.dawan.auth;

import fr.dawan.auth.dtos.LoginDto;
import fr.dawan.auth.dtos.LoginResponseDto;
import fr.dawan.auth.dtos.RegisterDto;
import fr.dawan.business.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    
    private final UserRepository userRepository;
    private final AuthMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    
    
    @Override
    public void register(RegisterDto register) {
        register.setPassword(passwordEncoder.encode(register.getPassword()));
        userRepository.saveAndFlush(mapper.fromRegister(register));
    }
    
    @Override
    public LoginResponseDto authenticate(LoginDto login) throws SecurityException {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.email(), login.password(), new ArrayList<>()));
        if (authenticate.isAuthenticated()) {
            log.info("Successful authentication for user with email {} at {}", login.email(), LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
            return mapper.toResponse((UserSecurity) authenticate.getPrincipal());
        }
        throw new SecurityException("Invalid Credentials");
    }
}
