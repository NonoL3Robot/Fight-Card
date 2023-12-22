package fr.dawan.business.user;

import fr.dawan.business.generic.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper extends GenericMapper<User, UserDto> {

    @Override
    @Mapping(source = "users", target = "userCount", qualifiedByName = "userConverter")
    UserDto toDto(User entity);

    @Named("userConverter")
    default long usersToUserCount(List<User> users) {
        if (users == null) return 0;
        return users.size();
    }
}
