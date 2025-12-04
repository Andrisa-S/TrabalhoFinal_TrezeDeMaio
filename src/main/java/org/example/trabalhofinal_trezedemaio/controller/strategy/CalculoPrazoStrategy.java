package org.example.trabalhofinal_trezedemaio.controller.strategy;

import java.time.LocalDate;

public interface CalculoPrazoStrategy {
    LocalDate calcularPrazo(LocalDate dataRetirada);
}
