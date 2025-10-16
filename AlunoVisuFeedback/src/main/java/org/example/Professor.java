package org.example;

import java.time.LocalDateTime;

public class Professor {
    private String nome;
    private Aluno aluno;

    public Professor(String nome, Aluno aluno) {
        this.nome = nome;
        this.aluno = aluno;
    }

    public Feedback criarFeedback(String conteudo, String disciplina) {
        return new Feedback(conteudo, this, this.aluno, disciplina, LocalDateTime.now());
    }

    public String getNome() { return nome; }
    public Aluno getAluno() { return aluno; }

    @Override
    public String toString() {
        return nome;
    }
}
