package fr.dawan.auth.dtos;

import fr.dawan.business.user.Roles;
import lombok.Value;

import java.util.Set;

@Value
public class LoginResponseDto {
    UserDto user;
    String token;
    
    @Value
    public static class UserDto {
        long id;
        String username;
        String email;
        Set<Roles> roles;
    }
}
