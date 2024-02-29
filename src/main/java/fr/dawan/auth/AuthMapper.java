package fr.dawan.auth;

import fr.dawan.auth.dtos.LoginResponseDto;
import fr.dawan.auth.dtos.RegisterDto;
import fr.dawan.business.user.User;
import fr.dawan.business.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {UserMapper.class})
public interface AuthMapper {
    
    User fromRegister(RegisterDto registerDto);
    
    @Mapping(target = "token", expression = "java(fr.dawan.auth.tools.JwtUtils.generateToken(security))")
    LoginResponseDto toResponse(UserSecurity security);
}
