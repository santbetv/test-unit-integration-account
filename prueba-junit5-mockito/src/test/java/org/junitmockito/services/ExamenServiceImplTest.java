package org.junitmockito.services;

import org.junit.jupiter.api.Test;
import org.junitmockito.models.Examen;
import org.junitmockito.repositories.ExamenRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExamenServiceImplTest {

    @Test
    void findExamenPorNombre() {

        //Arrange
        ExamenRepository repository = mock(ExamenRepository.class);
        ExamenService service = new ExamenServiceImpl(repository);
        List<Examen> examenes = Arrays.asList(new Examen(1L,"Matematica")
                ,new Examen(5L,"Lenguaje")
                ,new Examen(3L,"Historia"));

        //Act

        when(repository.findAll()).thenReturn(examenes);
        Optional<Examen> examen = service.findExamenPorNombre("Matematica");

        //Assert

        assertTrue(examen.isPresent());
        assertEquals(1L,examen.get().getId());
        assertEquals("Matematica",examen.get().getNombre());
    }
}