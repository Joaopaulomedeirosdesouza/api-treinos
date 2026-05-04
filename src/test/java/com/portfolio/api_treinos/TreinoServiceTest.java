package com.portfolio.api_treinos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.portfolio.api_treinos.infra.RegraDeNegocioException;
import com.portfolio.api_treinos.model.Exercicio;
import com.portfolio.api_treinos.model.Treino;
import com.portfolio.api_treinos.model.Usuario;
import com.portfolio.api_treinos.repository.ExercicioRepository;
import com.portfolio.api_treinos.repository.TreinoRepository;
import com.portfolio.api_treinos.service.TreinoService;

@ExtendWith(MockitoExtension.class)
class TreinoServiceTest {

    @Mock
    private TreinoRepository treinoRepository;

    @Mock
    private ExercicioRepository exercicioRepository;

    @InjectMocks
    private TreinoService service;

    @Test
    void deveCriarTreinoComSucesso() {
        Treino treino = new Treino("Treino A", "Peito");

        when(treinoRepository.save(treino)).thenReturn(treino);

        Treino resultado = service.criarTreino(treino);

        assertNotNull(resultado);
        assertEquals("Treino A", resultado.getNome());
        verify(treinoRepository).save(treino);
    }

    @Test
    void deveListarTreinosPorUsuario() {
        Usuario usuario = new Usuario("João", "joao@email.com", "123456");
        Treino treino = new Treino("Treino A", "Peito");
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Treino> page = new PageImpl<>(List.of(treino));

        when(treinoRepository.findByUsuario(usuario, pageable)).thenReturn(page);

        Page<Treino> resultado = service.listarPorUsuario(usuario, pageable);

        assertEquals(1, resultado.getTotalElements());
        verify(treinoRepository).findByUsuario(usuario, pageable);
    }

    @Test
    void deveAdicionarExercicioAoTreino() {
        Treino treino = new Treino("Treino A", "Peito");
        Exercicio exercicio = new Exercicio("Supino", 4, 12, 80.0, null);

        when(treinoRepository.findById(1L)).thenReturn(Optional.of(treino));
        when(exercicioRepository.save(exercicio)).thenReturn(exercicio);

        Exercicio resultado = service.adicionarExercicio(1L, exercicio);

        assertNotNull(resultado);
        assertEquals(treino, resultado.getTreino());
        verify(exercicioRepository).save(exercicio);
    }

    @Test
    void deveLancarExcecaoAoAdicionarExercicioEmTreinoInexistente() {
        Exercicio exercicio = new Exercicio("Supino", 4, 12, 80.0, null);

        when(treinoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RegraDeNegocioException.class, () -> service.adicionarExercicio(99L, exercicio));

        verify(exercicioRepository, never()).save(any());
    }

    @Test
    void deveDeletarTreinoComSucesso() {
        Treino treino = new Treino("Treino A", "Peito");

        when(treinoRepository.findById(1L)).thenReturn(Optional.of(treino));

        service.deletarTreino(1L);

        verify(treinoRepository).delete(treino);
    }

    @Test
    void deveLancarExcecaoAoDeletarTreinoInexistente() {
        when(treinoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RegraDeNegocioException.class, () -> service.deletarTreino(99L));

        verify(treinoRepository, never()).delete(any());
    }

    @Test
    void deveAtualizarTreinoComSucesso() {
        Treino treinoExistente = new Treino("Treino A", "Peito");
        Treino dadosAtualizados = new Treino("Treino B", "Costas");

        when(treinoRepository.findById(1L)).thenReturn(Optional.of(treinoExistente));
        when(treinoRepository.save(treinoExistente)).thenReturn(treinoExistente);

        Treino resultado = service.atualizarTreino(1L, dadosAtualizados);

        assertEquals("Treino B", resultado.getNome());
        assertEquals("Costas", resultado.getFocoMuscular());
        verify(treinoRepository).save(treinoExistente);
    }

    @Test
    void deveLancarExcecaoAoAtualizarTreinoInexistente() {
        Treino dadosAtualizados = new Treino("Treino B", "Costas");

        when(treinoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RegraDeNegocioException.class, () -> service.atualizarTreino(99L, dadosAtualizados));

        verify(treinoRepository, never()).save(any());
    }
}