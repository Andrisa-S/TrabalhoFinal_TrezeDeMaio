package org.example.trabalhofinal_trezedemaio.controller;

import org.example.trabalhofinal_trezedemaio.model.Autor;
import org.example.trabalhofinal_trezedemaio.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/autor")
public class AutorController {
    @Autowired
    private AutorRepository autorRepository;

    @GetMapping
    public String listar(@RequestParam(value = "busca", required = false) String busca, Model model) {
        if (busca != null && !busca.isEmpty()){
            model.addAttribute("autores", autorRepository.findByNomeAutorContainingIgnoreCase(busca));
        }
        else{
            model.addAttribute("autores", autorRepository.findAll());
        }
        return "autor/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("autor", new Autor());
        return "autor/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(Autor autor) {
        autorRepository.save(autor);
        return  "redirect:/autor";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Optional<Autor> autor = autorRepository.findById(id);
        if (autor.isPresent()) {
            model.addAttribute("autor", autor.get());
            return "autor/formulario";
        }
        return "redirect:/autor";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        autorRepository.deleteById(id);
        return "redirect:/autor";
    }
}
