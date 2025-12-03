package org.example.trabalhofinal_trezedemaio.model;

import jakarta.persistence.*;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String subtitulo;
    private String editora;
    private String isbn;
    private String edicao;
    private String localPublicacao;
    private Integer anoPublicacao;
    private Integer numPaginas;
    private String chamada;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "doador_id")
    private Doador doador;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getTitulo() {return titulo;}

    public void setTitulo(String titulo) {this.titulo = titulo;}

    public String getSubtitulo() {return subtitulo;}

    public void setSubtitulo(String subtitulo) {this.subtitulo = subtitulo;}

    public String getEditora() {return editora;}

    public void setEditora(String editora) {this.editora = editora;}

    public String getIsbn() {return isbn;}

    public void setIsbn(String isbn) {this.isbn = isbn;}

    public String getEdicao() {return edicao;}

    public void setEdicao(String edicao) {this.edicao = edicao;}

    public String getLocalPublicacao() {return localPublicacao;}

    public void setLocalPublicacao(String localPublicacao) {this.localPublicacao = localPublicacao;}

    public Integer getAnoPublicacao() {return anoPublicacao;}

    public void setAnoPublicacao(Integer anoPublicacao) {this.anoPublicacao = anoPublicacao;}

    public Integer getNumPaginas() {return numPaginas;}

    public void setNumPaginas(Integer numPaginas) {this.numPaginas = numPaginas;}

    public String getChamada() {return chamada;}

    public void setChamada(String chamada) {this.chamada = chamada;}

    public Autor getAutor() {return autor;}

    public void setAutor(Autor autor) {this.autor = autor;}

    public Doador getDoador() {return doador;}

    public void setDoador(Doador doador) {this.doador = doador;}
}
