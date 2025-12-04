package org.example.trabalhofinal_trezedemaio.controller;

import org.example.trabalhofinal_trezedemaio.controller.facade.EmprestimoFacade;
import org.example.trabalhofinal_trezedemaio.controller.strategy.*;
import org.example.trabalhofinal_trezedemaio.model.Emprestimo;
import org.example.trabalhofinal_trezedemaio.repository.EmprestimoRepository;
import org.example.trabalhofinal_trezedemaio.repository.LivroRepository;
import org.example.trabalhofinal_trezedemaio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/emprestimo")
public class EmprestimoController {
    @Autowired
    private EmprestimoFacade emprestimoFacade;

    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("emprestimos", emprestimoRepository.findAll());
        return "emprestimo/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("emprestimo", new Emprestimo());
        model.addAttribute("listaLivros", livroRepository.findAll());
        model.addAttribute("listaUsuarios", usuarioRepository.findAll());

        return "emprestimo/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(Emprestimo emprestimo) {
        emprestimoFacade.registrarEmprestimo(emprestimo);
        return "redirect:/emprestimo";
    }

    @GetMapping("/devolver/{id}")
    public String devolver(@PathVariable("id") Long id) {
        emprestimoFacade.registrarDevolucao(id);
        return "redirect:/emprestimo";
    }
}
