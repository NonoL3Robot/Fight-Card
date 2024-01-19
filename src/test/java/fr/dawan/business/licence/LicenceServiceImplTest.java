package fr.dawan.business.licence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // Permet l'utilisation de mocks
class LicenceServiceImplTest {

    @Mock
    private LicenceRepository repository;
    @Mock// Crée une simulation de l'objet à utiliser
    private LicenceMapper mapper;
    private LicenceServiceImpl service;

    @BeforeEach // s'exécute AVANT chaque test
    void setup() {
        service = new LicenceServiceImpl(repository, mapper);
    }

    @Test
    void findAllTest() {
        // Arrange
        Pageable pageable = Pageable.unpaged();

        Licence licence1 = new Licence("Marvel", null);
        Licence licence2 = new Licence("Marvel", null);
        Page<Licence> entities = new PageImpl<>(List.of(licence1, licence2));

        LicenceDto licenceDto1 = new LicenceDto(0L, 0, licence1.getName(), null, 0);
        LicenceDto licenceDto2 = new LicenceDto(0L, 0, licence1.getName(), null, 0);
        List<LicenceDto> expected = List.of(licenceDto1, licenceDto2);

        Mockito.when(repository.findAll(pageable)).thenReturn(entities);
        Mockito.when(mapper.toDto(licence1)).thenReturn(licenceDto1);
        Mockito.when(mapper.toDto(licence2)).thenReturn(licenceDto2);

        // Act
        Page<LicenceDto> result = service.findAll(pageable);

        // Assert
        assertTrue(result.getContent().containsAll(expected));
    }

    @Test
    void passingTest() {
        assertTrue(true);
    }

}