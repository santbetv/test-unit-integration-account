package org.junitmockito.repositories;

import org.junitmockito.models.Examen;

import java.util.List;

public interface ExamenRepository {
    List<Examen> findAll();
}
