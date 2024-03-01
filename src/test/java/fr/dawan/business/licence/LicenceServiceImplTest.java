package fr.dawan.business.licence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class) // Permet l'utilisation de mocks
class LicenceServiceImplTest {
    
    @Mock
    private LicenceRepository repository;
    @Mock// Crée une simulation de l'objet à utiliser
    private LicenceMapper mapper;
    private LicenceServiceImpl service;
    
    private ApplicationEventPublisher publisher;
    
    @BeforeEach
        // s'exécute AVANT chaque test
    void setup() {
        service = new LicenceServiceImpl(repository, mapper, publisher);
    }
    
    @Test
    void findAllTest() {
        Pageable pageable = Pageable.unpaged();
        
        Licence licence1 = new Licence("Test1", null);
        Licence licence2 = new Licence("Test2", null);
        Page<Licence> entities = new PageImpl<>(List.of(licence1, licence2));
        
        LicenceDto licenceDto1 = new LicenceDto(0L, 0, licence1.getName(), null);
        LicenceDto licenceDto2 = new LicenceDto(0L, 0, licence1.getName(), null);
        List<LicenceDto> expected = List.of(licenceDto1, licenceDto2);
        
        Mockito.when(repository.findAll(pageable)).thenReturn(entities);
        Mockito.when(mapper.toDto(licence1)).thenReturn(licenceDto1);
        Mockito.when(mapper.toDto(licence2)).thenReturn(licenceDto2);
        
        Page<LicenceDto> result = service.findAll(pageable);
        
        assertTrue(result.getContent().containsAll(expected));
    }
    
    @Test
    void findByIdTest() {
        Licence licence1 = new Licence("Test", null);
        Optional<Licence> entity = Optional.of(licence1);
        LicenceDto licenceDto1 = new LicenceDto(0L, 0, licence1.getName(), null);
        
        Mockito.when(repository.findById(licence1.getId())).thenReturn(entity);
        Mockito.when(mapper.toDto(licence1)).thenReturn(licenceDto1);
        
        Optional<LicenceDto> result = service.findById(licence1.getId());
        
        result.ifPresent(res -> assertEquals(res.getId(), licence1.getId()));
    }
    
}