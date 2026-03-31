package com.portfolio.api_treinos.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.api_treinos.dto.UsuarioRequestDTO;
import com.portfolio.api_treinos.dto.UsuarioResponseDTO;
import com.portfolio.api_treinos.model.Usuario;
import com.portfolio.api_treinos.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO cadastrar(@RequestBody @Valid UsuarioRequestDTO dto) {

        Usuario novoUsuario = new Usuario(dto.nome(), dto.email(), dto.senha());

        Usuario usuarioSalvo = service.cadastrar(novoUsuario);

        return new UsuarioResponseDTO(usuarioSalvo);
    }
}