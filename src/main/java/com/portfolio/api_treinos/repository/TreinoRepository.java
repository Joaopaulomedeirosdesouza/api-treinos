package com.portfolio.api_treinos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.portfolio.api_treinos.model.Usuario;
import com.portfolio.api_treinos.model.Treino;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Long> {
    Page<Treino> findByUsuario(Usuario usuario, Pageable pageable);

}
