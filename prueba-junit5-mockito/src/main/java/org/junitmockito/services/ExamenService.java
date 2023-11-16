package org.junitmockito.services;

import org.junitmockito.models.Examen;

import java.util.Optional;

public interface ExamenService {


    Optional<Examen> findExamenPorNombre(String nombre);

    Examen findExamenPorNombreConPreguntas(String nombre);

    Examen guardar(Examen examen);
}
