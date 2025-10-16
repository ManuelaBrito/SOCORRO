package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Feedback {
    private String conteudo;
    private Professor professor;
    private Aluno aluno;
    private String disciplina;
    private LocalDateTime dataCriacao;

    public Feedback(String conteudo, Professor professor, Aluno aluno, String disciplina, LocalDateTime dataCriacao) {
        this.conteudo = conteudo;
        this.professor = professor;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.dataCriacao = dataCriacao;
    }

    public boolean podeSerAcessadoPor(Aluno aluno) {
        return this.aluno.getEmail().equals(aluno.getEmail());
    }

    public String getConteudo() { return conteudo; }
    public Professor getProfessor() { return professor; }
    public Aluno getAluno() { return aluno; }
    public String getDisciplina() { return disciplina; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public String getAlunoEmail() { return aluno.getEmail(); }

    public String getDataFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dataCriacao.format(formatter);
    }

    public String getResumo() {
        return "Disciplina: " + disciplina +
                "\nProfessor: " + professor.getNome() +
                "\nData: " + getDataFormatada();
    }

    @Override
    public String toString() {
        return disciplina + " - " + professor.getNome() + " (" + getDataFormatada() + ")";
    }
}
