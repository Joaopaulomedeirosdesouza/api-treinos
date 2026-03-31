package com.portfolio.api_treinos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.portfolio.api_treinos.model.Exercicio;
import com.portfolio.api_treinos.model.Treino;
import com.portfolio.api_treinos.repository.ExercicioRepository;
import com.portfolio.api_treinos.repository.TreinoRepository;

@Service
public class TreinoService {

    private final TreinoRepository treinoRepository;
    private final ExercicioRepository exercicioRepository;

    public TreinoService(TreinoRepository treinoRepository, ExercicioRepository exercicioRepository) {
        this.treinoRepository = treinoRepository;
        this.exercicioRepository = exercicioRepository;
    }

    public Treino criarTreino(Treino treino) {
        return treinoRepository.save(treino);
    }

    public List<Treino> listarTodos() {
        return treinoRepository.findAll();
    }

    public Exercicio adicionarExercicio(Long idTreino, Exercicio exercicio) {

        Treino treino = treinoRepository.findById(idTreino)
                .orElseThrow(() -> new RuntimeException("Treino não encontrado!"));

        exercicio.setTreino(treino);

        return exercicioRepository.save(exercicio);
    }

    public void deletarTreino(Long id) {

        Treino treino = treinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Treino não encontrado!"));

        treinoRepository.delete(treino);
    }

    public Treino atualizarTreino(Long id, Treino dadosAtualizados) {

        Treino treinoExistente = treinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Treino não encontrado!"));

        treinoExistente.setNome(dadosAtualizados.getNome());
        treinoExistente.setFocoMuscular(dadosAtualizados.getFocoMuscular());

        return treinoRepository.save(treinoExistente);
    }
}