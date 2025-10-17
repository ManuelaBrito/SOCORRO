package org.example;

    public class TesteVisualizacaoTG {
        public static void main(String[] args) {
            // Configurar dados de exemplo
            GerenciadorOrientacoes gerenciador = new GerenciadorOrientacoes();

            // Criar professores orientadores
            Professor prof1 = new Professor("Dr. Carlos Silva");
            Professor prof2 = new Professor("Dra. Ana Santos");
            Professor prof3 = new Professor("Dr. Paulo Oliveira");
            Professor prof4 = new Professor("Dra. Mariana Costa");

            gerenciador.adicionarProfessor(prof1);
            gerenciador.adicionarProfessor(prof2);
            gerenciador.adicionarProfessor(prof3);
            gerenciador.adicionarProfessor(prof4);

            // Criar alunos
            Aluno aluno1 = new Aluno("João Pereira", "joao@email.com");
            Aluno aluno2 = new Aluno("Maria Souza", "maria@email.com");
            Aluno aluno3 = new Aluno("Pedro Costa", "pedro@email.com");
            Aluno aluno4 = new Aluno("Ana Lima", "ana@email.com");
            Aluno aluno5 = new Aluno("Carlos Rocha", "carlos@email.com");
            Aluno aluno6 = new Aluno("Julia Mendes", "julia@email.com");
            Aluno aluno7 = new Aluno("Lucas Oliveira", "lucas@email.com");
            Aluno aluno8 = new Aluno("Fernanda Silva", "fernanda@email.com");

            // Vincular alunos aos professores
            prof1.adicionarAluno(aluno1);
            prof1.adicionarAluno(aluno2);
            prof1.adicionarAluno(aluno3);

            prof2.adicionarAluno(aluno4);
            prof2.adicionarAluno(aluno5);

            prof3.adicionarAluno(aluno6);

            // aluno7 e aluno8 ficarão sem orientador

            // Adicionar todos os alunos ao gerenciador
            gerenciador.adicionarAluno(aluno1);
            gerenciador.adicionarAluno(aluno2);
            gerenciador.adicionarAluno(aluno3);
            gerenciador.adicionarAluno(aluno4);
            gerenciador.adicionarAluno(aluno5);
            gerenciador.adicionarAluno(aluno6);
            gerenciador.adicionarAluno(aluno7);
            gerenciador.adicionarAluno(aluno8);

            // Usar a visualização específica para o professor de TG
            VisualizacaoDistribuicaoTG visualizacaoTG = new VisualizacaoDistribuicaoTG(gerenciador);

            System.out.println("=== MODO PROFESSOR DE TG - VISÃO COMPLETA ===");
            visualizacaoTG.exibirDistribuicaoGeral();

            System.out.println("\n\n=== VISÃO RÁPIDA ===");
            visualizacaoTG.exibirResumoRapido();

            System.out.println("\n\n=== CONSULTA ESPECÍFICA ===");
            visualizacaoTG.exibirAlunosPorProfessor("Dr. Carlos Silva");

            System.out.println("\n=== ESTATÍSTICAS DO SISTEMA ===");
            gerenciador.exibirEstatisticasBasicas();
        }
    }

