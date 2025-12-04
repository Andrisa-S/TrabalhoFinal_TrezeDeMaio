package org.example.trabalhofinal_trezedemaio.controller.strategy;

import java.time.LocalDate;

public class PrazoComumStrategy implements CalculoPrazoStrategy{
    @Override
    public LocalDate calcularPrazo(LocalDate dataRetirada) {
        return dataRetirada.plusDays(7);
    }
}
