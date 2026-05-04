package com.portfolio.api_treinos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.portfolio.api_treinos.infra.RegraDeNegocioException;
import com.portfolio.api_treinos.model.Usuario;
import com.portfolio.api_treinos.repository.UsuarioRepository;
import com.portfolio.api_treinos.service.UsuarioService;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService service;

    @Test
    void deveCadastrarUsuarioComSucesso() {
        Usuario usuario = new Usuario("João", "joao@email.com", "123456");

        when(repository.findByEmail("joao@email.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("123456")).thenReturn("senhaCriptografada");
        when(repository.save(usuario)).thenReturn(usuario);

        Usuario resultado = service.cadastrar(usuario);

        assertNotNull(resultado);
        assertEquals("senhaCriptografada", resultado.getSenha());
        verify(repository).save(usuario);
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaExiste() {
        Usuario usuario = new Usuario("João", "joao@email.com", "123456");

        when(repository.findByEmail("joao@email.com")).thenReturn(Optional.of(usuario));

        assertThrows(RegraDeNegocioException.class, () -> service.cadastrar(usuario));

        verify(repository, never()).save(any());
    }
}