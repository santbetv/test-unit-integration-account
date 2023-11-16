package org.junitmockito.services;

import org.junitmockito.models.Examen;
import org.junitmockito.repositories.ExamenRepository;

import java.util.Optional;

public class ExamenServiceImpl implements ExamenService{

    private ExamenRepository examenRepository;

    public ExamenServiceImpl(ExamenRepository examenRepository) {
        this.examenRepository = examenRepository;
    }

    @Override
    public Optional<Examen> findExamenPorNombre(String nombre) {
        Optional<Examen> examen = examenRepository.findAll().stream().filter(data -> data.getNombre().contains(nombre)).findFirst();
        return examen;
    }
}
