package fr.dawan.auth;

import fr.dawan.auth.dtos.LoginDto;
import fr.dawan.auth.dtos.LoginResponseDto;
import fr.dawan.auth.dtos.RegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
    private final AuthService authService;
    
    @PostMapping(value = "/authenticate", consumes = "application/json")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginDto login) throws Exception {
        return ResponseEntity.ok(authService.authenticate(login));
    }
    
    @PostMapping(value = "/register", consumes = "application/json")
    public void register(@RequestBody RegisterDto newUser) {
        authService.register(newUser);
    }
}
