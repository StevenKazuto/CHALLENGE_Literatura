package com.example.challenge.challenge_literatura.repository;

import com.example.challenge.challenge_literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTituloContainsIgnoreCase(String nombreLibro);

    @Query("SELECT l FROM Libro l WHERE l.idiomasList = :idioma")
    List<Libro> librosPorIdioma(String idioma);
}
