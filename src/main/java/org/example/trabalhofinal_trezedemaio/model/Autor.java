package org.example.trabalhofinal_trezedemaio.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeAutor;
    private String nacionalidade;
    private LocalDate dataNascimento;
    private LocalDate dataFalecimento;

    @Column(columnDefinition = "TEXT")
    private String biografiaResumida;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNomeAutor() {return nomeAutor;}

    public void setNomeAutor(String nomeAutor) {this.nomeAutor = nomeAutor;}

    public String getNacionalidade() {return nacionalidade;}

    public void setNacionalidade(String nacionalidade) {this.nacionalidade = nacionalidade;}

    public LocalDate getDataNascimento() {return dataNascimento;}

    public void setDataNascimento(LocalDate dataNascimento) {this.dataNascimento = dataNascimento;}

    public LocalDate getDataFalecimento() {return dataFalecimento;}

    public void setDataFalecimento(LocalDate dataFalecimento) {this.dataFalecimento = dataFalecimento;}

    public String getBiografiaResumida() {return biografiaResumida;}

    public void setBiografiaResumida(String biografiaResumida) {this.biografiaResumida = biografiaResumida;}
}
