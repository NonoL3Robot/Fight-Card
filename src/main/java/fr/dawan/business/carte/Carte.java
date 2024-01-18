package fr.dawan.business.carte;

import fr.dawan.business.generic.BaseEntity;
import fr.dawan.business.licence.Licence;
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

    @ManyToOne
    private Licence licence;

    private String description;

    private int statCourage;
    private int statIntelligence;
    private int statForce;
}
