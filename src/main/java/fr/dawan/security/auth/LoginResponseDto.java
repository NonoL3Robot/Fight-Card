package fr.dawan.security.auth;

import fr.dawan.security.tools.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponseDto {
    private long id;
    private String username;
    private String token;
    private List<String> roles;
    
    public LoginResponseDto(UserSecurity userSecurity) {
        id = userSecurity.getUser().getId();
        username = userSecurity.getUser().getUsername();
        token = JwtUtils.generateToken(userSecurity);
        roles = userSecurity.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
    }
}
