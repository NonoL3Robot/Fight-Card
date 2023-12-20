package fr.dawan.business.licence;

import fr.dawan.business.carte.CarteLicenceDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LicenceCarteDto {
    private long id;
    private String name;
}
