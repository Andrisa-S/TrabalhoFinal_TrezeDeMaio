package org.example.trabalhofinal_trezedemaio.repository;

import org.example.trabalhofinal_trezedemaio.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro,Long> {
    List<Livro> findByTituloContainingIgnoreCase(String busca);
}
