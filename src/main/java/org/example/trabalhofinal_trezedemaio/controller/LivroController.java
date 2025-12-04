package org.example.trabalhofinal_trezedemaio.controller;

import org.example.trabalhofinal_trezedemaio.model.Livro;
import org.example.trabalhofinal_trezedemaio.repository.AutorRepository;
import org.example.trabalhofinal_trezedemaio.repository.DoadorRepository;
import org.example.trabalhofinal_trezedemaio.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
@RequestMapping("/livro")
public class LivroController {
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private DoadorRepository doadorRepository;

    @GetMapping
    public String listar(@RequestParam(value = "busca", required = false) String busca, Model model) {
        if (busca != null && !busca.isEmpty()) {
            model.addAttribute("livros", livroRepository.findByTituloContainingIgnoreCase(busca));
        } else {
            model.addAttribute("livros", livroRepository.findAll());
        }
        return "livro/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("livro", new Livro());

        model.addAttribute("listaAutores", autorRepository.findAll());
        model.addAttribute("listaDoadores", doadorRepository.findAll());

        return "livro/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(Livro livro) {
        livroRepository.save(livro);
        return "redirect:/livro";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Optional<Livro> livro = livroRepository.findById(id);

        if (livro.isPresent()) {
            model.addAttribute("livro", livro.get());

            model.addAttribute("listaAutores", autorRepository.findAll());
            model.addAttribute("listaDoadores", doadorRepository.findAll());

            return "livro/formulario";
        }
        return "redirect:/livro";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id) {
        livroRepository.deleteById(id);
        return "redirect:/livro";
    }
}
