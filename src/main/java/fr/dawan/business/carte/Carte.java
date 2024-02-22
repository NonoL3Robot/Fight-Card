package fr.dawan.business.carte;

import fr.dawan.business.licence.Licence;
import fr.dawan.generic.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Carte extends BaseEntity {
    private String name;
    private String description;
    private int statCourage;
    private int statIntelligence;
    private int statForce;
    
    @ManyToOne
    private Licence licence;
}
