package org.example.trabalhofinal_trezedemaio.model.builder;

import org.example.trabalhofinal_trezedemaio.model.Autor;
import org.example.trabalhofinal_trezedemaio.model.Doador;
import org.example.trabalhofinal_trezedemaio.model.Livro;

public class LivroBuilder {
    private Livro livro;

    public LivroBuilder() {
        this.livro = new Livro();
    }

    public static LivroBuilder criar() {
        return new LivroBuilder();
    }

    public LivroBuilder comTitulo(String titulo) {
        this.livro.setTitulo(titulo);
        return this;
    }

    public LivroBuilder doAutor(Autor autor) {
        this.livro.setAutor(autor);
        return this;
    }

    public LivroBuilder doadoPor(Doador doador) {
        this.livro.setDoador(doador);
        return this;
    }

    public LivroBuilder detalhes(String isbn, String editora, Integer ano) {
        this.livro.setIsbn(isbn);
        this.livro.setEditora(editora);
        this.livro.setAnoPublicacao(ano);
        return this;
    }

    public Livro build() {
        return this.livro;
    }
}
