package com.sumaunplato.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Acceso a datos de Categoria. JpaRepository ya provee
 * save, findAll, findById, deleteById, etc.
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
