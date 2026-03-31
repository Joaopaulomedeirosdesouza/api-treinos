package com.portfolio.api_treinos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TreinoRequestDTO(

        @NotBlank(message = "O nome do treino é obrigatório e não pode ficar em branco.") @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.") String nome,

        @NotBlank(message = "O foco muscular não pode ficar em branco.") String focoMuscular) {
}