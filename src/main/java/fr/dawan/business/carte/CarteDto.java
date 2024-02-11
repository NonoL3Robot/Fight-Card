package fr.dawan.business.carte;

import fr.dawan.business.licence.LicenceDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarteDto {
    private long id;
    private int version;
    private String name;
    private String description;
    private LicenceDto licence;
}
