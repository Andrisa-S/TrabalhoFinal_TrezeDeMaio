package org.example.trabalhofinal_trezedemaio.repository;

import org.example.trabalhofinal_trezedemaio.model.AcervoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AcervoHistoricoRepository extends JpaRepository<AcervoHistorico, Long> {
    
    // Busca por descrição (ignorando case)
    List<AcervoHistorico> findByDescricaoContainingIgnoreCase(String descricao);
    
    // Busca por tipo de item
    List<AcervoHistorico> findByTipoItemContainingIgnoreCase(String tipoItem);
    
    // Busca por doador
    List<AcervoHistorico> findByDoadorId(Long doadorId);
    
    // Busca por ano do item
    List<AcervoHistorico> findByAnoItem(Integer anoItem);
    
    // Busca por ano do item entre um intervalo
    List<AcervoHistorico> findByAnoItemBetween(Integer anoInicio, Integer anoFim);
    
    // Consulta personalizada: itens sem caminho (não digitalizados)
    @Query("SELECT a FROM AcervoHistorico a WHERE a.caminhoItem IS NULL OR a.caminhoItem = ''")
    List<AcervoHistorico> findNaoDigitalizados();
    
    // Consulta personalizada: contar itens por tipo
    @Query("SELECT a.tipoItem, COUNT(a) FROM AcervoHistorico a GROUP BY a.tipoItem")
    List<Object[]> countByTipoItem();
}