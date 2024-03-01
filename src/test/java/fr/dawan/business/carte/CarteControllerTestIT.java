package fr.dawan.business.carte;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
    private CarteRestController controller;
    
    @Autowired
    private CarteMapper carteMapper;
    
    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        
        cartes = new ArrayList<>();
        cartes.add(new CarteDto(1L, 0, "Carte1", "carte1 pour test1", 0, 0, 0, 0));
        cartes.add(new CarteDto(2L, 0, "Carte2", "carte2 pour test2", 0, 0, 0, 0));
    }
    
    // 1 methode de test pour chaque methode dans CarteController
    
    @Test
    void deleteById_test() throws Exception {
        long id = 1;
        
        when(service.findById(id)).thenReturn(Optional.ofNullable(cartes.get(0)));
        doNothing().when(service).deleteById(id);
        
        mockMvc.perform(
                        delete("http://localhost:8080/api/v1/cartes/{id}", id)
                                .with(csrf())
                )
                .andExpect(status().isOk()); // Adapté à votre structure de réponse
    }
//    @Test
//    void update_test() throws Exception {
//        CarteDto carteDto = new CarteDto(1L, 0, "Carte1", "carte1 pour test1", new CarteLicenceDto());
//        when(service.getById(1L)).thenReturn(carteDto);
//        when(service.update(any())).thenReturn(carteDto);
//
//        String carteJson = objectMapper.writeValueAsString(carteDto);
//
//        mockMvc.perform(put("/cartes")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(carteJson))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is(carteDto.getName())));
//    }
}