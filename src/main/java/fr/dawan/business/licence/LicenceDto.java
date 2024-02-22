package fr.dawan.business.licence;

import fr.dawan.business.carte.CarteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LicenceDto {
    private long id;
    private int version;
    private String name;
    private List<CarteDto> cartes;
}
