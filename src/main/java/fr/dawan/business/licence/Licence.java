package fr.dawan.business.licence;

import fr.dawan.business.carte.Carte;
import fr.dawan.generic.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Licence extends BaseEntity {
    private String name;
    
    @OneToMany(mappedBy = "licence")
    private List<Carte> cartes;
}
