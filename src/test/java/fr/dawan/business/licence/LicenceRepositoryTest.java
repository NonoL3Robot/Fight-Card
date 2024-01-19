package fr.dawan.business.licence;

import fr.dawan.business.carte.Carte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class LicenceRepositoryTest {

    @Autowired
    LicenceRepository licenceRepository;

    private Licence l;

    @BeforeEach
    public void beforeEach() {
        l = new Licence("Licence 1", List.of(new Carte()));
    }

    @Test
    void save_Test() {
        Licence saved = licenceRepository.save(l);
        assertNotNull(saved);
        assertThat(saved.getId()).isPositive();
    }

    @Test
    void update_Test() {
        Licence saved = licenceRepository.save(l);
        Optional<Licence> licence = licenceRepository.findById(saved.getId());
        assertTrue(licence.isPresent());
        licence.get().setName("New Licence");
        List<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte("Test", licence.get(), "", 0, 0, 0));
        licence.get().setCartes(cartes);
        Licence updated = licenceRepository.save(licence.get());
        assertNotNull(updated);
        assertEquals(licence.get().getName(), updated.getName());
        assertEquals(licence.get().getCartes().get(0).getName(), updated.getCartes().get(0).getName());
    }

    @Test
    void delete_Test() {
        Licence saved = licenceRepository.save(l);
        licenceRepository.deleteById(saved.getId());
        Optional<Licence> optional = licenceRepository.findById(saved.getId());
        assertThat(optional).isEmpty();
    }

    @Test
    void findAll_Test() {
        licenceRepository.save(l);
        licenceRepository.save(new Licence("Licence 2", List.of(new Carte())));
        List<Licence> licenceList = licenceRepository.findAll();
        assertEquals(licenceRepository.findAll().size(), licenceList.size());
    }

    @Test
    void findById_Test() {
        licenceRepository.save(l);
        Optional<Licence> licence = licenceRepository.findById(l.getId());
        assertTrue(licence.isPresent());
        assertEquals(l.getName(), licence.get().getName());
    }

}