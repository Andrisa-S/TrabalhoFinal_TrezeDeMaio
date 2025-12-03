package org.example.trabalhofinal_trezedemaio.model;

import jakarta.persistence.*;

@Entity
public class AcervoHistorico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoItem;
    private String descricao;
    private Integer anoItem;
    private String caminhoItem;

    @ManyToOne
    @JoinColumn(name = "doador_id")
    private Doador doador;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getTipoItem() {return tipoItem;}

    public void setTipoItem(String tipoItem) {this.tipoItem = tipoItem;}

    public String getDescricao() {return descricao;}

    public void setDescricao(String descricao) {this.descricao = descricao;}

    public Integer getAnoItem() {return anoItem;}

    public void setAnoItem(Integer anoItem) {this.anoItem = anoItem;}

    public String getCaminhoItem() {return caminhoItem;}

    public void setCaminhoItem(String caminhoItem) {this.caminhoItem = caminhoItem;}

    public Doador getDoador() {return doador;}

    public void setDoador(Doador doador) {this.doador = doador;}
}
