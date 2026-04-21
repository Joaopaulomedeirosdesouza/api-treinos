package com.portfolio.api_treinos.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import com.portfolio.api_treinos.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.portfolio.api_treinos.model.Exercicio;
import com.portfolio.api_treinos.model.Treino;
import com.portfolio.api_treinos.service.TreinoService;
import com.portfolio.api_treinos.dto.TreinoRequestDTO;
import com.portfolio.api_treinos.dto.TreinoResponseDTO;
import com.portfolio.api_treinos.dto.ExercicioRequestDTO;
import com.portfolio.api_treinos.dto.ExercicioResponseDTO;

@RestController
@RequestMapping("/treinos")
public class TreinoController {

    private final TreinoService service;

    public TreinoController(TreinoService service) {
        this.service = service;
    }

    @GetMapping
    public Page<TreinoResponseDTO> listar(Pageable pageable) {

        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return service.listarPorUsuario(usuarioLogado, pageable).map(TreinoResponseDTO::new);
    }

    @PostMapping("/{id}/exercicios")
    @ResponseStatus(HttpStatus.CREATED)
    public ExercicioResponseDTO adicionarExercicioAoTreino(
            @PathVariable("id") Long idTreino,
            @RequestBody @Valid ExercicioRequestDTO dto) {

        Exercicio novoExercicio = new Exercicio(dto.nome(), dto.series(), dto.repeticoes(), dto.cargaAtual(), null);
        Exercicio exercicioSalvo = service.adicionarExercicio(idTreino, novoExercicio);

        return new ExercicioResponseDTO(exercicioSalvo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TreinoResponseDTO criar(@RequestBody @Valid TreinoRequestDTO dto) {

        Treino novoTreino = new Treino(dto.nome(), dto.focoMuscular());

        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        novoTreino.setUsuario(usuarioLogado);

        Treino treinoSalvo = service.criarTreino(novoTreino);

        return new TreinoResponseDTO(treinoSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable("id") Long id) {
        service.deletarTreino(id);
    }

    @PutMapping("/{id}")
    public TreinoResponseDTO atualizar(@PathVariable("id") Long id, @RequestBody @Valid TreinoRequestDTO dto) {

        Treino dadosParaAtualizar = new Treino(dto.nome(), dto.focoMuscular());
        Treino treinoAtualizado = service.atualizarTreino(id, dadosParaAtualizar);

        return new TreinoResponseDTO(treinoAtualizado);
    }
}