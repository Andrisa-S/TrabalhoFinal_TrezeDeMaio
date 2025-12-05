package org.example.trabalhofinal_trezedemaio.config;

import org.example.trabalhofinal_trezedemaio.model.*;
import org.example.trabalhofinal_trezedemaio.model.builder.LivroBuilder; // Importante para o Padrão Builder
import org.example.trabalhofinal_trezedemaio.repository.*;
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
    @Autowired private AcervoHistoricoRepository acervoHistoricoRepository;

    @Override
    public void run(String... args) throws Exception {

        if (autorRepository.count() == 0) {
            Autor a1 = new Autor();
            a1.setNomeAutor("Machado de Assis");
            a1.setNacionalidade("Brasileiro");
            a1.setBiografiaResumida("Fundador da Academia Brasileira de Letras.");
            a1.setDataNascimento(LocalDate.of(1839, 6, 21));
            a1.setDataFalecimento(LocalDate.of(1908, 9, 29)); // Falecido

            Autor a2 = new Autor();
            a2.setNomeAutor("Clarice Lispector");
            a2.setNacionalidade("Brasileira");
            a2.setBiografiaResumida("Uma das maiores escritoras brasileiras do século XX.");
            a2.setDataNascimento(LocalDate.of(1920, 12, 10));
            a2.setDataFalecimento(LocalDate.of(1977, 12, 9)); // Falecida

            Autor a3 = new Autor();
            a3.setNomeAutor("George Orwell");
            a3.setNacionalidade("Inglês");
            a3.setBiografiaResumida("Jornalista e romancista britânico.");
            a3.setDataNascimento(LocalDate.of(1903, 6, 25));
            a3.setDataFalecimento(LocalDate.of(1950, 1, 21)); // Falecido

            Autor a4 = new Autor();
            a4.setNomeAutor("Paulo Coelho");
            a4.setNacionalidade("Brasileiro");
            a4.setBiografiaResumida("Autor brasileiro mais traduzido no mundo.");
            a4.setDataNascimento(LocalDate.of(1947, 8, 24));
            a4.setDataFalecimento(null); // Único autor vivo (nulo) conforme pedido

            autorRepository.saveAll(Arrays.asList(a1, a2, a3, a4));
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
            l1.setIsbn("978-85-999");
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

            Livro l4 = new Livro();
            l4.setTitulo("O Alquimista");
            l4.setAutor(autores.get(3));
            l4.setDoador(doadores.get(0));
            l4.setAnoPublicacao(1988);
            l4.setEditora("Paralela");
            l4.setIsbn("978-85-123");
            livroRepository.save(l4);
        }

        if (acervoHistoricoRepository.count() == 0 && doadorRepository.count() > 0) {
            List<Doador> doadores = doadorRepository.findAll();

            AcervoHistorico h1 = new AcervoHistorico();
            h1.setTipoItem("Fotografia");
            h1.setDescricao("Foto original da inauguração da Estação Ferroviária de Santa Maria");
            h1.setAnoItem(1885);
            h1.setCaminhoItem("/imagens/historico/estacao_1885.jpg");
            h1.setDoador(doadores.get(2));

            AcervoHistorico h2 = new AcervoHistorico();
            h2.setTipoItem("Documento");
            h2.setDescricao("Ata de fundação do Clube Recreativo Treze de Maio");
            h2.setAnoItem(1903);
            h2.setCaminhoItem("/docs/historico/ata_fundacao_1903.pdf");
            h2.setDoador(doadores.get(0));

            AcervoHistorico h3 = new AcervoHistorico();
            h3.setTipoItem("Objeto");
            h3.setDescricao("Máquina de escrever Olivetti usada na secretaria na década de 60");
            h3.setAnoItem(1965);
            h3.setCaminhoItem("/imagens/historico/olivetti_1965.png");
            h3.setDoador(doadores.get(1));

            acervoHistoricoRepository.saveAll(Arrays.asList(h1, h2, h3));
        }
    }
}