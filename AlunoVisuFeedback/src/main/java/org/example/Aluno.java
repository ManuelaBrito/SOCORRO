package org.example;

public class Aluno {
    private String nome;
    private String email;
    private String orientador;

    public Aluno(String nome, String email, String orientador) {
        this.nome = nome;
        this.email = email;
        this.orientador = orientador;
    }

    public String visualizarFeedback(Feedback feedback) {
        if (feedback.getAlunoEmail().equals(this.email)) {
            return feedback.getConteudo();
        } else {
            return "Acesso negado: Este feedback não é para você.";
        }
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getOrientador() { return orientador; }

    @Override
    public String toString() {
        return nome + " (" + email + ")";
    }
}