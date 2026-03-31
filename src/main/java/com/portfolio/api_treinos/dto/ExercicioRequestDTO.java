package com.portfolio.api_treinos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ExercicioRequestDTO(

        @NotBlank(message = "O nome do exercício é obrigatório.") String nome,

        @NotNull(message = "O número de séries é obrigatório.") @Positive(message = "O número de séries deve ser maior que zero.") Integer series,

        @NotNull(message = "O número de repetições é obrigatório.") @Positive(message = "O número de repetições deve ser maior que zero.") Integer repeticoes,

        @Positive(message = "A carga deve ser maior que zero.") Double cargaAtual) {
}