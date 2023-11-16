package org.junitmockito.services;

import org.junitmockito.models.Examen;

import java.util.Optional;

public interface ExamenService {

    public Optional<Examen> findExamenPorNombre(String nombre);
}
