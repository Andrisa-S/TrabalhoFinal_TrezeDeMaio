package org.example.trabalhofinal_trezedemaio.controller.facade;

import org.example.trabalhofinal_trezedemaio.controller.strategy.CalculoPrazoStrategy;
import org.example.trabalhofinal_trezedemaio.controller.strategy.PrazoComumStrategy;
import org.example.trabalhofinal_trezedemaio.controller.strategy.PrazoEstendidoStrategy;
import org.example.trabalhofinal_trezedemaio.model.Emprestimo;
import org.example.trabalhofinal_trezedemaio.model.Usuario;
import org.example.trabalhofinal_trezedemaio.repository.EmprestimoRepository;
import org.example.trabalhofinal_trezedemaio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EmprestimoFacade {
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void registrarEmprestimo(Emprestimo emprestimo) {
        if (emprestimo.getDataRetirada() == null) {
            emprestimo.setDataRetirada(LocalDate.now());
        }

        Usuario usuario = usuarioRepository.findById(emprestimo.getUsuario().getId()).orElse(null);

        if (usuario != null) {
            CalculoPrazoStrategy estrategia;

            if (usuario.getTipo().equalsIgnoreCase("Pesquisador") ||
                    usuario.getTipo().equalsIgnoreCase("Estudante")) {
                estrategia = new PrazoEstendidoStrategy();
            } else {
                estrategia = new PrazoComumStrategy();
            }
            LocalDate dataCalculada = estrategia.calcularPrazo(emprestimo.getDataRetirada());
            emprestimo.setDataPrevista(dataCalculada);
        }
        emprestimoRepository.save(emprestimo);
    }

    public void registrarDevolucao(Long idEmprestimo) {
        Optional<Emprestimo> emp = emprestimoRepository.findById(idEmprestimo);
        if (emp.isPresent()) {
            Emprestimo e = emp.get();
            e.setDataDevolucao(LocalDate.now());
            emprestimoRepository.save(e);
        }
    }
}
