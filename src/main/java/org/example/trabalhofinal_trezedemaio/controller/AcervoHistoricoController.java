package org.example.trabalhofinal_trezedemaio.controller;

import org.example.trabalhofinal_trezedemaio.model.AcervoHistorico;
import org.example.trabalhofinal_trezedemaio.repository.AcervoHistoricoRepository;
import org.example.trabalhofinal_trezedemaio.repository.DoadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.Optional;

@Controller
@RequestMapping("/acervo-historico")
public class AcervoHistoricoController {
	@Autowired
    private AcervoHistoricoRepository acervoHistoricoRepository;
    @Autowired
    private DoadorRepository doadorRepository;
    
    @GetMapping
    public String listar(@RequestParam(value = "busca", required = false) String busca, 
                         @RequestParam(value = "tipo", required = false) String tipo,
                         Model model) {
        
        if (busca != null && !busca.isEmpty()) {
            model.addAttribute("itens", acervoHistoricoRepository.findByDescricaoContainingIgnoreCase(busca));
        } else if (tipo != null && !tipo.isEmpty()) {
            model.addAttribute("itens", acervoHistoricoRepository.findByTipoItemContainingIgnoreCase(tipo));
        } else {
            model.addAttribute("itens", acervoHistoricoRepository.findAll());
        }
        
        model.addAttribute("tiposItens", new String[]{"Fotografia", "Carta", "Jornal", "Ata", "Objeto", "Documento", "Áudio", "Vídeo"});
        return "acervo-historico/lista";
    }
    
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("acervo", new AcervoHistorico());
        model.addAttribute("listaDoadores", doadorRepository.findAll());
        model.addAttribute("tiposItens", new String[]{"Fotografia", "Carta", "Jornal", "Ata", "Objeto", "Documento", "Áudio", "Vídeo"});
        return "acervo-historico/formulario";
    }
    
    @PostMapping("/salvar")
    public String salvar(AcervoHistorico acervoHistorico) {
        acervoHistoricoRepository.save(acervoHistorico);
        return "redirect:/acervo-historico";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Optional<AcervoHistorico> acervo = acervoHistoricoRepository.findById(id);
        
        if (acervo.isPresent()) {
            model.addAttribute("acervo", acervo.get());
            model.addAttribute("listaDoadores", doadorRepository.findAll());
            model.addAttribute("tiposItens", new String[]{"Fotografia", "Carta", "Jornal", "Ata", "Objeto", "Documento", "Áudio", "Vídeo"});
            return "acervo-historico/formulario";
        }
        return "redirect:/acervo-historico";
    }
    
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id) {
        acervoHistoricoRepository.deleteById(id);
        return "redirect:/acervo-historico";
    }
    
    @GetMapping("/detalhes/{id}")
    public String detalhes(@PathVariable("id") Long id, Model model) {
        Optional<AcervoHistorico> acervo = acervoHistoricoRepository.findById(id);
        
        if (acervo.isPresent()) {
            model.addAttribute("acervo", acervo.get());
            return "acervo-historico/detalhes";
        }
        return "redirect:/acervo-historico";
    }
    
    @GetMapping("/por-doador")
    public String buscarPorDoador(@RequestParam("doadorId") Long doadorId, Model model) {
        model.addAttribute("itens", acervoHistoricoRepository.findById(doadorId));
        model.addAttribute("tiposItens", new String[]{"Fotografia", "Carta", "Jornal", "Ata", "Objeto", "Documento", "Áudio", "Vídeo"});
        return "acervo-historico/lista";
    }
}
