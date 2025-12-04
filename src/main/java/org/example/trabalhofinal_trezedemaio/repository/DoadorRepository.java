package org.example.trabalhofinal_trezedemaio.repository;

import org.example.trabalhofinal_trezedemaio.model.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoadorRepository extends JpaRepository<Doador,Long> {
    List<Doador> findByNomeContainingIgnoreCase(String nome);
}
