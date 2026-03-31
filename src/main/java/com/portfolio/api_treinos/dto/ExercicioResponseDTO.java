package com.portfolio.api_treinos.dto;

import com.portfolio.api_treinos.model.Exercicio;

public record ExercicioResponseDTO(
        Long id,
        String nome,
        Integer series,
        Integer repeticoes,
        Double cargaAtual) {

    public ExercicioResponseDTO(Exercicio exercicio) {
        this(
                exercicio.getId(),
                exercicio.getNome(),
                exercicio.getSeries(),
                exercicio.getRepeticoes(),
                exercicio.getCargaAtual());
    }
}