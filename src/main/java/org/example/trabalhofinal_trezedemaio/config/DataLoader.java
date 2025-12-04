package org.example.trabalhofinal_trezedemaio.config;

import org.example.trabalhofinal_trezedemaio.model.Autor;
import org.example.trabalhofinal_trezedemaio.model.Doador;
import org.example.trabalhofinal_trezedemaio.model.Livro;
import org.example.trabalhofinal_trezedemaio.model.Usuario;
import org.example.trabalhofinal_trezedemaio.model.builder.LivroBuilder; // Importante para o Padrão Builder
import org.example.trabalhofinal_trezedemaio.repository.AutorRepository;
import org.example.trabalhofinal_trezedemaio.repository.DoadorRepository;
import org.example.trabalhofinal_trezedemaio.repository.LivroRepository;
import org.example.trabalhofinal_trezedemaio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired private LivroRepository livroRepository;
    @Autowired private DoadorRepository doadorRepository;
    @Autowired private AutorRepository autorRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {

        if (autorRepository.count() == 0) {
            Autor a1 = new Autor();
            a1.setNomeAutor("Machado de Assis");
            a1.setNacionalidade("Brasileiro");
            a1.setDataNascimento(LocalDate.of(1839, 6, 21));

            Autor a2 = new Autor();
            a2.setNomeAutor("Clarice Lispector");
            a2.setNacionalidade("Brasileira");
            a2.setBiografiaResumida("Uma das maiores escritoras brasileiras do século XX.");

            Autor a3 = new Autor();
            a3.setNomeAutor("George Orwell");
            a3.setNacionalidade("Inglês");

            autorRepository.saveAll(Arrays.asList(a1, a2, a3));
        }

        if (doadorRepository.count() == 0) {
            Doador d1 = new Doador();
            d1.setNome("Maria da Silva");
            d1.setContato("maria@email.com");

            Doador d2 = new Doador();
            d2.setNome("João Pedro Santos");
            d2.setContato("(55) 9999-8888");

            Doador d3 = new Doador();
            d3.setNome("Biblioteca Municipal");
            d3.setContato("admin@biblioteca.sm.gov.br");

            doadorRepository.saveAll(Arrays.asList(d1, d2, d3));
        }

        if (usuarioRepository.count() == 0) {
            Usuario u1 = new Usuario();
            u1.setNome("Carlos Visitante");
            u1.setEmail("carlos@gmail.com");
            u1.setSenha("123");
            u1.setTipo("Visitante");

            Usuario u2 = new Usuario();
            u2.setNome("Ana Estudante");
            u2.setEmail("ana@ufn.edu.br");
            u2.setSenha("123");
            u2.setTipo("Estudante");

            Usuario u3 = new Usuario();
            u3.setNome("Prof. Roberto Pesquisador");
            u3.setEmail("roberto@pesquisa.com");
            u3.setSenha("123");
            u3.setTipo("Pesquisador");

            usuarioRepository.saveAll(Arrays.asList(u1, u2, u3));
        }

        if (livroRepository.count() == 0 && autorRepository.count() > 0) {
            List<Autor> autores = autorRepository.findAll();
            List<Doador> doadores = doadorRepository.findAll();

            Livro l1 = new Livro();
            l1.setTitulo("Dom Casmurro");
            l1.setAutor(autores.get(0));
            l1.setDoador(doadores.get(0));
            l1.setAnoPublicacao(1899);
            l1.setEditora("Editora Garnier");
            livroRepository.save(l1);

            Livro l2 = LivroBuilder.criar()
                    .comTitulo("A Hora da Estrela")
                    .doAutor(autores.get(1))
                    .doadoPor(doadores.get(1))
                    .detalhes("978-853250812X", "Rocco", 1977)
                    .build();
            livroRepository.save(l2);

            Livro l3 = LivroBuilder.criar()
                    .comTitulo("1984")
                    .doAutor(autores.get(2))
                    .doadoPor(doadores.get(2))
                    .detalhes("123-456-789", "Companhia das Letras", 1949)
                    .build();
            l3.setChamada("823 O79n");
            livroRepository.save(l3);
        }
    }
}