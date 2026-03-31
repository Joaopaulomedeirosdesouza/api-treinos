package com.portfolio.api_treinos.dto;

import java.util.List;
import com.portfolio.api_treinos.model.Treino;

public record TreinoResponseDTO(
        Long id,
        String nome,
        String focoMuscular,

        List<ExercicioResponseDTO> exercicios) {
    public TreinoResponseDTO(Treino treino) {
        this(
                treino.getId(),
                treino.getNome(),
                treino.getFocoMuscular(),

                treino.getExercicios() != null ? treino.getExercicios().stream().map(ExercicioResponseDTO::new).toList()
                        : List.of());
    }
}