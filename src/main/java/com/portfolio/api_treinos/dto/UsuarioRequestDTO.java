package com.portfolio.api_treinos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(

        @NotBlank(message = "O nome é obrigatório.") String nome,

        @NotBlank(message = "O email é obrigatório.") @Email(message = "O formato do email é inválido.") String email,

        @NotBlank(message = "A senha é obrigatória.") @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.") String senha) {
}