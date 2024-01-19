package fr.dawan.business.licence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LicenceDto {
    private long id;
    private int version;
    private String name;
    private long carteCount;

}
