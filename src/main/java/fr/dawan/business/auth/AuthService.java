package fr.dawan.business.auth;

public interface AuthService {
    void register(RegisterDto registerDto);
    LoginResponseDto login(LoginDto loginDto) throws Exception;
}
