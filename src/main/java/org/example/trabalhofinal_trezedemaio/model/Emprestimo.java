package org.example.trabalhofinal_trezedemaio.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataRetirada = LocalDate.now();
    private LocalDate dataDevolucao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public LocalDate getDataRetirada() {return dataRetirada;}

    public void setDataRetirada(LocalDate dataRetirada) {this.dataRetirada = dataRetirada;}

    public LocalDate getDataDevolucao() {return dataDevolucao;}

    public void setDataDevolucao(LocalDate dataDevolucao) {this.dataDevolucao = dataDevolucao;}

    public Usuario getUsuario() {return usuario;}

    public void setUsuario(Usuario usuario) {this.usuario = usuario;}

    public Livro getLivro() {return livro;}

    public void setLivro(Livro livro) {this.livro = livro;}
}
