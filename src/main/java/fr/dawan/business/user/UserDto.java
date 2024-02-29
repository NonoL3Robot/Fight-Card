package fr.dawan.business.user;

import fr.dawan.business.carte.CarteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private int version;
    private String email;
    Set<Roles> roles;
}
