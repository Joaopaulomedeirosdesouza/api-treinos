package com.portfolio.api_treinos.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.portfolio.api_treinos.infra.RegraDeNegocioException;
import com.portfolio.api_treinos.model.Usuario;
import com.portfolio.api_treinos.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario cadastrar(Usuario usuario) {
        if (repository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RegraDeNegocioException("Este email já está em uso na plataforma!");
        }

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        log.info("Novo usuário cadastrado: {}", usuario.getEmail());
        return repository.save(usuario);
    }
}