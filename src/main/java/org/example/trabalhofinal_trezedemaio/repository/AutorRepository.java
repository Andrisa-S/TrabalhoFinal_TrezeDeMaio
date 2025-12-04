package org.example.trabalhofinal_trezedemaio.repository;

import org.example.trabalhofinal_trezedemaio.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByNomeAutorContainingIgnoreCase(String nome);
}
