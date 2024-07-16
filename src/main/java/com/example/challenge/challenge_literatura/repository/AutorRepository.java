package com.example.challenge.challenge_literatura.repository;

import com.example.challenge.challenge_literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {

    Optional<Autor> findByNombreIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fechaDenacimiento < :fecha AND a.fechaDeMuerte > :fecha")
    List<Autor> autoresVivos(Long fecha);


}
