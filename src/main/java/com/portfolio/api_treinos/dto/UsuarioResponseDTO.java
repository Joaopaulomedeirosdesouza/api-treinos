package com.portfolio.api_treinos.dto;

import com.portfolio.api_treinos.model.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email) {
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}