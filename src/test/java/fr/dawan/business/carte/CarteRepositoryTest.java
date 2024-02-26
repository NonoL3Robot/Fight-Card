package fr.dawan.business.carte;

import fr.dawan.business.licence.Licence;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CarteRepositoryTest {
    
    @Autowired
    private CarteRepository carteRepository;
    
    private Carte c;
    
    @BeforeEach
    public void beforeEach() {
        c = new Carte("Test", "description", 0, 0, 0, new Licence());
    }
    
    @Test
    void save_test() {
        Carte saved = carteRepository.save(c);
        assertNotNull(saved);
        AssertionsForClassTypes.assertThat(saved.getId()).isPositive();
    }
    
    @Test
    void findById_Test() {
        carteRepository.save(c);
        Optional<Carte> carte = carteRepository.findById(c.getId());
        assertTrue(carte.isPresent());
        assertEquals(c.getName(), carte.get().getName());
    }
    
    @Test
    void findAll_Test() {
        carteRepository.save(c);
        carteRepository.save(new Carte("", "", 0, 0, 0, new Licence("", new ArrayList<>())));
        List<Carte> cartes = carteRepository.findAll();
        assertEquals(carteRepository.findAll().size(), cartes.size());
    }
    
    @Test
    void update_test() {
        Carte saved = carteRepository.save(c);
        Optional<Carte> carte = carteRepository.findById(saved.getId());
        assertTrue(carte.isPresent());
        carte.get().setName("New Carte");
        Carte updated = carteRepository.save(carte.get());
        assertNotNull(updated);
        assertEquals(carte.get().getName(), updated.getName());
    }
    
    @Test
    void delete_test() {
        Carte saved = carteRepository.save(c);
        carteRepository.deleteById(saved.getId());
        
        Optional<Carte> deleted = carteRepository.findById(saved.getId());
        assertThat(deleted).isEmpty();
    }
}