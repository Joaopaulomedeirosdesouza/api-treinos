package com.portfolio.api_treinos.service;

import org.springframework.stereotype.Service;
import com.portfolio.api_treinos.model.Exercicio;
import com.portfolio.api_treinos.model.Treino;
import com.portfolio.api_treinos.model.Usuario;
import com.portfolio.api_treinos.repository.ExercicioRepository;
import com.portfolio.api_treinos.repository.TreinoRepository;
import com.portfolio.api_treinos.infra.RegraDeNegocioException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TreinoService {

    private final TreinoRepository treinoRepository;
    private final ExercicioRepository exercicioRepository;
    private static final Logger log = LoggerFactory.getLogger(TreinoService.class);

    public TreinoService(TreinoRepository treinoRepository, ExercicioRepository exercicioRepository) {
        this.treinoRepository = treinoRepository;
        this.exercicioRepository = exercicioRepository;
    }

    public Treino criarTreino(Treino treino) {
        log.info("Treino criado: {}", treino.getNome());
        return treinoRepository.save(treino);
    }

    public Page<Treino> listarPorUsuario(Usuario usuario, Pageable pageable) {
        return treinoRepository.findByUsuario(usuario, pageable);
    }

    public Exercicio adicionarExercicio(Long idTreino, Exercicio exercicio) {

        Treino treino = treinoRepository.findById(idTreino)
                .orElseThrow(() -> new RegraDeNegocioException("Treino não encontrado!"));

        exercicio.setTreino(treino);

        return exercicioRepository.save(exercicio);
    }

    public void deletarTreino(Long id) {

        Treino treino = treinoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Treino não encontrado!"));
        log.info("Treino deletado: id {}", id);
        treinoRepository.delete(treino);
    }

    public Treino atualizarTreino(Long id, Treino dadosAtualizados) {

        Treino treinoExistente = treinoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Treino não encontrado!"));

        treinoExistente.setNome(dadosAtualizados.getNome());
        treinoExistente.setFocoMuscular(dadosAtualizados.getFocoMuscular());
        log.info("Treino atualizado: id {}", id);
        return treinoRepository.save(treinoExistente);
    }
}