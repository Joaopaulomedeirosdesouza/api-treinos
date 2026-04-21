package com.portfolio.api_treinos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_exercicios")
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer series;
    private Integer repeticoes;
    private Double cargaAtual;

    @ManyToOne
    @JoinColumn(name = "treino_id")
    private Treino treino;

    public Exercicio() {
    }

    public Exercicio(String nome, Integer series, Integer repeticoes, Double cargaAtual, Treino treino) {
        this.nome = nome;
        this.series = series;
        this.repeticoes = repeticoes;
        this.cargaAtual = cargaAtual;
        this.treino = treino;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(Integer repeticoes) {
        this.repeticoes = repeticoes;
    }

    public Double getCargaAtual() {
        return cargaAtual;
    }

    public void setCargaAtual(Double cargaAtual) {
        this.cargaAtual = cargaAtual;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }
}