package fr.dawan.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("auth")
@RestController
public class AuthController {
    private final AuthService service;
    
    @PostMapping("login")
    public LoginResponseDto authenticate(@RequestBody LoginDto credential) {
        return service.authenticate(credential);
    }
    
    @PostMapping("register")
    public void register(@RequestBody RegisterDto dto) {
        service.register(dto);
    }
}
