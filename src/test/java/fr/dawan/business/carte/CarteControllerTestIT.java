package fr.dawan.business.carte;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.business.licence.Licence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
 class CarteControllerTestIT {

     private List<CarteDto> cartes;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarteService service;

    @Autowired
    private CarteController controller;

    @Autowired
    private CarteMapper carteMapper;


    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        cartes = new ArrayList<>();
        cartes.add(new CarteDto(1L, 0, "Carte1", "carte1 pour test1", new CarteLicenceDto()));
        cartes.add(new CarteDto(2L, 0, "Carte2", "carte2 pour test2", new CarteLicenceDto()));
    }

    @Test
    void getByLicenceId_test() throws Exception {
        Pageable pageable = Pageable.unpaged();
        long id = 2L;
        //Carte carte = carteMapper.toEntity(cartes.get(1));
        cartes.clear();
        CarteDto expected = new CarteDto(2L, 0, "Carte2", "carte2 pour test2", new CarteLicenceDto());
        Page<Carte> entity = new PageImpl<>(List.of(new Carte("jhon", new Licence(),"doe", 5,5,5)), pageable, 1);
        when(service.getById(id, pageable)).thenReturn(entity);

        mockMvc.perform(get("/cartes/byLicenceId/{id}", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[1].id", is(cartes.get(1).getId())))
                .andExpect(jsonPath("$.content[1].name", is(cartes.get(1).getName())));
    }

    @Test
    void getById_CarteNull_ReturnNotFound() throws Exception {
        long id = 3L;
        when(service.getById(id)).thenReturn(null);

        mockMvc.perform(get("/cartes/{id}", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Carte with id = " + id + " not found")));
    }

    // 1 methode de test pour chaque methode dans CarteController
    /*
    @Test
    void deleteById_test() throws Exception {
        long id = 1;

        when(service.getById(id)).thenReturn(cartes.get(0));
        doNothing().when(service).deleteById(id);

        mockMvc.perform(delete("/cartes/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Carte supprimée avec succès"))); // Adapté à votre structure de réponse
    }
    @Test
    void update_test() throws Exception {
        CarteDto carteDto = new CarteDto(1L, 0, "Carte1", "carte1 pour test1", new CarteLicenceDto());
        when(service.getById(1L)).thenReturn(carteDto);
        when(service.update(any())).thenReturn(carteDto);

        String carteJson = objectMapper.writeValueAsString(carteDto);

        mockMvc.perform(put("/cartes")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carteJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(carteDto.getName())));
    }*/
}
