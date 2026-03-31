package com.portfolio.api_treinos.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_treinos")
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String focoMuscular;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "treino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exercicio> exercicios = new ArrayList<>();

    public Treino() {
    }

    public Treino(String nome, String focoMuscular) {
        this.nome = nome;
        this.focoMuscular = focoMuscular;
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

    public String getFocoMuscular() {
        return focoMuscular;
    }

    public void setFocoMuscular(String focoMuscular) {
        this.focoMuscular = focoMuscular;
    }

    public void getUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Exercicio> getExercicios() {
        return exercicios;
    }

    public void setExercicios(List<Exercicio> exercicios) {
        this.exercicios = exercicios;
    }

    public void adicionarExercicio(Exercicio exercicio) {
        exercicios.add(exercicio);
        exercicio.setTreino(this);
    }
}