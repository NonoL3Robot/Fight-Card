package fr.dawan.business.user;

import fr.dawan.generic.GenericMapper;
import fr.dawan.security.auth.RegisterDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends GenericMapper<UserDto, User> {
    User toEntity(RegisterDto dto);
}
