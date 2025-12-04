package org.example.trabalhofinal_trezedemaio.controller;

import org.example.trabalhofinal_trezedemaio.model.Doador;
import org.example.trabalhofinal_trezedemaio.repository.DoadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/doador")
public class DoadorController {
    @Autowired
    private DoadorRepository doadorRepository;

    @GetMapping
    public String listaDoador(Model model) {
        model.addAttribute("doadores", doadorRepository.findAll());
        return "doador/lista";
    }
}
