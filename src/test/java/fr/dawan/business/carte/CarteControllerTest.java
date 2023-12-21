package fr.dawan.business.carte;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({CarteController.class, CarteService.class, CarteMapper.class})
class CarteControllerTest {
    @Autowired
    private MockMvc mockMvc; // Simuler une requête HTTP dans notre application

    @MockBean // Crée un mock dans le contexte de Spring (pour un test d'intégration)
    private CarteRepository repository;

    @Test
    void findByName() throws Exception {
        // Arrange
        String name = "Un";
        Pageable pageable = PageRequest.of(0, 20);

        Carte carte1 = new Carte("Un article", null);
        Carte carte2 = new Carte("Un Article Deux", null);
        Page<Carte> entities = new PageImpl<>(List.of(carte1, carte2));

        Mockito.when(repository.findByNameLike("%" + name + "%", pageable)).thenReturn(entities);

        // Act
        mockMvc.perform( // Lance une requête
            MockMvcRequestBuilders.get("/cartes/byName/" + name)
        ).andDo(print())
        //Assert
            .andExpect(
                status().isOk()
            ).andExpect(
                jsonPath("$").isNotEmpty()
            );
    }
}