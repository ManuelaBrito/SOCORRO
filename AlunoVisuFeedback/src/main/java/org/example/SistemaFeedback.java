package org.example;

import java.util.ArrayList;
import java.util.List;

public class SistemaFeedback {
    private List<Feedback> feedbacks;
    private Aluno alunoLogado;

    public SistemaFeedback() {
        this.feedbacks = new ArrayList<>();
        inicializarDadosExemplo();
    }

    private void inicializarDadosExemplo() {
        // Criar alunos
        Aluno alunoJoao = new Aluno("João Santos", "joao@email.com", "Prof. Carlos");
        Aluno alunoMaria = new Aluno("Maria Oliveira", "maria@email.com", "Prof. Ana");

        // Criar professores
        Professor profCarlos = new Professor("Prof. Carlos", alunoJoao);
        Professor profAna = new Professor("Prof. Ana", alunoMaria);

        // Criar feedbacks
        feedbacks.add(profCarlos.criarFeedback(
                "Prezado João,\n\nSeu desempenho no projeto de Java foi excelente. Você demonstrou:\n" +
                        "✅ Domínio dos conceitos de POO\n✅ Boas práticas de codificação\n✅ Capacidade de trabalhar em equipe\n\n" +
                        "Pontos para melhorar:\n📌 Trabalhar na documentação do código\n📌 Aprimorar os testes unitários\n\n" +
                        "Nota: 9.0\nContinue com o bom trabalho!\n\nAtenciosamente,\nProf. Carlos",
                "Programação Java"
        ));

        feedbacks.add(profCarlos.criarFeedback(
                "Caro João,\n\nSeu trabalho sobre banco de dados foi muito bem elaborado. Destaques:\n" +
                        "✅ Modelagem ER precisa\n✅ Consultas SQL otimizadas\n✅ Documentação completa\n\n" +
                        "Sugestões:\n📌 Explorar mais índices no banco\n📌 Implementar stored procedures\n\n" +
                        "Nota: 8.5\nExcelente evolução!\n\nAtt.,\nProf. Carlos",
                "Banco de Dados"
        ));

        feedbacks.add(profAna.criarFeedback(
                "Cara Maria,\n\nSeu projeto de interface foi muito criativo. Pontos fortes:\n" +
                        "✅ Design intuitivo e moderno\n✅ Usabilidade excelente\n✅ Prototipagem bem executada\n\n" +
                        "Áreas de desenvolvimento:\n📌 Aprofundar em acessibilidade\n📌 Testar com mais usuários\n\n" +
                        "Nota: 9.2\nParabéns pelo ótimo trabalho!\n\nAtt.,\nProf. Ana",
                "Design de Interface"
        ));
    }

    public boolean loginAluno(String email) {
        if (email.equals("joao@email.com")) {
            this.alunoLogado = new Aluno("João Santos", "joao@email.com", "Prof. Carlos");
            return true;
        } else if (email.equals("maria@email.com")) {
            this.alunoLogado = new Aluno("Maria Oliveira", "maria@email.com", "Prof. Ana");
            return true;
        }
        return false;
    }

    public List<Feedback> getFeedbacksAlunoLogado() {
        List<Feedback> feedbacksAluno = new ArrayList<>();
        if (alunoLogado != null) {
            for (Feedback feedback : feedbacks) {
                if (feedback.podeSerAcessadoPor(alunoLogado)) {
                    feedbacksAluno.add(feedback);
                }
            }
        }
        return feedbacksAluno;
    }

    public Aluno getAlunoLogado() {
        return alunoLogado;
    }

    public boolean isAlunoLogado() {
        return alunoLogado != null;
    }
}
