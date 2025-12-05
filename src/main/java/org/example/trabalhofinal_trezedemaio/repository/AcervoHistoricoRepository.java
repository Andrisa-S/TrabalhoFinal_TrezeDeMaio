package org.example.trabalhofinal_trezedemaio.repository;

import org.example.trabalhofinal_trezedemaio.model.AcervoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AcervoHistoricoRepository extends JpaRepository<AcervoHistorico, Long> {
    
    List<AcervoHistorico> findByDescricaoContainingIgnoreCase(String descricao);
    List<AcervoHistorico> findByTipoItemContainingIgnoreCase(String tipoItem);
    List<AcervoHistorico> findByDoadorId(Long doadorId);
    List<AcervoHistorico> findByAnoItem(Integer anoItem);
    List<AcervoHistorico> findByAnoItemBetween(Integer anoInicio, Integer anoFim);
    
    @Query("SELECT a FROM AcervoHistorico a WHERE a.caminhoItem IS NULL OR a.caminhoItem = ''")
    List<AcervoHistorico> findNaoDigitalizados();
    
    @Query("SELECT a.tipoItem, COUNT(a) FROM AcervoHistorico a GROUP BY a.tipoItem")
    List<Object[]> countByTipoItem();
}