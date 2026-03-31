package com.portfolio.api_treinos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.api_treinos.model.Treino;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Long> {

}