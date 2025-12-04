package org.example.trabalhofinal_trezedemaio.controller;

import org.example.trabalhofinal_trezedemaio.model.Doador;
import org.example.trabalhofinal_trezedemaio.repository.DoadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
@RequestMapping("/doador")
public class DoadorController {
    @Autowired
    private DoadorRepository doadorRepository;

    @GetMapping
    public String listar(@RequestParam(value = "busca", required = false) String busca, Model model) {
        if (busca != null && !busca.isEmpty()) {
            model.addAttribute("doadores", doadorRepository.findByNomeContainingIgnoreCase(busca));
        } else {
            model.addAttribute("doadores", doadorRepository.findAll());
        }
        return "doador/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("doador", new Doador());
        return "doador/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(Doador doador) {
        doadorRepository.save(doador);
        return "redirect:/doador";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Optional<Doador> doador = doadorRepository.findById(id);

        if (doador.isPresent()) {
            model.addAttribute("doador", doador.get());
            return "doador/formulario";
        } else {
            return "redirect:/doador";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id) {
        doadorRepository.deleteById(id);
        return "redirect:/doador";
    }
}
