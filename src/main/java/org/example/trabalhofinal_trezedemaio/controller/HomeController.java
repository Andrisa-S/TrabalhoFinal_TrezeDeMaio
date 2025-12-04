package org.example.trabalhofinal_trezedemaio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "home/home";
    }

    @GetMapping("/construcao")
    public String emConstrucao() {
        return "construcao";
    }
}
