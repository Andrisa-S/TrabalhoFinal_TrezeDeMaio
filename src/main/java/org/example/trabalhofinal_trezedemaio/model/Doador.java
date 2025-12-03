package org.example.trabalhofinal_trezedemaio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Doador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String contato;
    private LocalDate dataCadastro = LocalDate.now();

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getContato() {return contato;}

    public void setContato(String contato) {this.contato = contato;}

    public LocalDate getDataCadastro() {return dataCadastro;}

    public void setDataCadastro(LocalDate dataCadastro) {this.dataCadastro = dataCadastro;}
}
