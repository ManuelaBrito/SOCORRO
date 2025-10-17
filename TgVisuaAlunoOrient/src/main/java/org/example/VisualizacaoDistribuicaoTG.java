package org.example;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

    public class VisualizacaoDistribuicaoTG {
        private GerenciadorOrientacoes gerenciador;

        public VisualizacaoDistribuicaoTG(GerenciadorOrientacoes gerenciador) {
            this.gerenciador = gerenciador;
        }

        // Método principal para o professor de TG visualizar a distribuição
        public void exibirDistribuicaoGeral() {
            System.out.println("🎓 **VISÃO DO PROFESSOR DE TG - DISTRIBUIÇÃO DE ORIENTAÇÕES**");
            System.out.println("==============================================================");

            List<Professor> professores = gerenciador.getProfessores();
            List<Aluno> todosAlunos = gerenciador.getAlunos();

            if (professores.isEmpty()) {
                System.out.println("❌ Nenhum professor cadastrado no sistema.");
                return;
            }

            if (todosAlunos.isEmpty()) {
                System.out.println("❌ Nenhum aluno cadastrado no sistema.");
                return;
            }

            // Agrupar alunos por orientador
            Map<Professor, List<Aluno>> alunosPorOrientador = todosAlunos.stream()
                    .filter(aluno -> aluno.getOrientador() != null)
                    .collect(Collectors.groupingBy(Aluno::getOrientador));

            // Exibir distribuição por professor
            System.out.println("\n📊 **DISTRIBUIÇÃO POR ORIENTADOR:**");
            System.out.println("----------------------------------------");

            for (Professor professor : professores) {
                List<Aluno> alunosDoProfessor = alunosPorOrientador.getOrDefault(professor, List.of());

                System.out.println("\n👨‍🏫 **Orientador: " + professor.getNome() + "**");
                System.out.println("   📈 Quantidade: " + alunosDoProfessor.size() + " aluno(s)");

                if (!alunosDoProfessor.isEmpty()) {
                    System.out.println("   👥 Alunos vinculados:");
                    for (int i = 0; i < alunosDoProfessor.size(); i++) {
                        Aluno aluno = alunosDoProfessor.get(i);
                        System.out.println("      " + (i + 1) + ". " + aluno.getNome() + " - " + aluno.getEmail());
                    }
                } else {
                    System.out.println("   ℹ️  Nenhum aluno vinculado");
                }
            }

            // Alunos sem orientador
            List<Aluno> alunosSemOrientador = gerenciador.getAlunosSemOrientador();

            System.out.println("\n⚠️  **ALUNOS SEM ORIENTADOR DEFINIDO:**");
            System.out.println("----------------------------------------");
            if (alunosSemOrientador.isEmpty()) {
                System.out.println("✅ Todos os alunos têm orientador definido!");
            } else {
                System.out.println("Quantidade: " + alunosSemOrientador.size() + " aluno(s)");
                for (int i = 0; i < alunosSemOrientador.size(); i++) {
                    Aluno aluno = alunosSemOrientador.get(i);
                    System.out.println("   " + (i + 1) + ". " + aluno.getNome() + " - " + aluno.getEmail());
                }
            }

            exibirRelatorioEstatistico();
        }

        private void exibirRelatorioEstatistico() {
            System.out.println("\n📈 **RELATÓRIO ESTATÍSTICO - VISÃO TG**");
            System.out.println("========================================");

            List<Professor> professores = gerenciador.getProfessores();
            List<Aluno> todosAlunos = gerenciador.getAlunos();

            long totalAlunos = todosAlunos.size();
            long alunosComOrientador = todosAlunos.stream()
                    .filter(aluno -> aluno.getOrientador() != null)
                    .count();

            System.out.println("👥 Total de alunos no sistema: " + totalAlunos);
            System.out.println("✅ Alunos com orientador: " + alunosComOrientador);
            System.out.println("❌ Alunos sem orientador: " + (totalAlunos - alunosComOrientador));

            // Estatísticas por professor
            if (alunosComOrientador > 0) {
                System.out.println("\n📋 **DISTRIBUIÇÃO POR ORIENTADOR:**");
                Map<Professor, Long> contagemPorProfessor = todosAlunos.stream()
                        .filter(aluno -> aluno.getOrientador() != null)
                        .collect(Collectors.groupingBy(Aluno::getOrientador, Collectors.counting()));

                for (Professor professor : professores) {
                    long quantidade = contagemPorProfessor.getOrDefault(professor, 0L);
                    double percentual = totalAlunos > 0 ? (quantidade * 100.0) / totalAlunos : 0;
                    System.out.printf("   %s: %d aluno(s) (%.1f%%)\n",
                            professor.getNome(), quantidade, percentual);
                }

                // Professor com mais/menos alunos (apenas entre os que têm alunos)
                List<Map.Entry<Professor, Long>> professoresComAlunos = contagemPorProfessor.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue() > 0)
                        .collect(Collectors.toList());

                if (!professoresComAlunos.isEmpty()) {
                    Map.Entry<Professor, Long> maisAlunos = professoresComAlunos.stream()
                            .max(Map.Entry.comparingByValue())
                            .get();

                    Map.Entry<Professor, Long> menosAlunos = professoresComAlunos.stream()
                            .min(Map.Entry.comparingByValue())
                            .get();

                    System.out.println("\n🏆 **DESTAQUES:**");
                    System.out.println("   Professor com mais alunos: " + maisAlunos.getKey().getNome() +
                            " (" + maisAlunos.getValue() + " alunos)");
                    System.out.println("   Professor com menos alunos: " + menosAlunos.getKey().getNome() +
                            " (" + menosAlunos.getValue() + " alunos)");
                }
            }
        }

        // Método para visualização simplificada (apenas números)
        public void exibirResumoRapido() {
            List<Aluno> todosAlunos = gerenciador.getAlunos();
            Map<Professor, Long> distribuicao = todosAlunos.stream()
                    .filter(aluno -> aluno.getOrientador() != null)
                    .collect(Collectors.groupingBy(Aluno::getOrientador, Collectors.counting()));

            System.out.println("🚀 **RESUMO RÁPIDO - DISTRIBUIÇÃO DE ORIENTAÇÕES**");
            System.out.println("===================================================");
            System.out.println("Total de alunos: " + todosAlunos.size());
            System.out.println("Alunos com orientador: " +
                    todosAlunos.stream().filter(a -> a.getOrientador() != null).count());

            System.out.println("\n📋 Distribuição por orientador:");
            if (distribuicao.isEmpty()) {
                System.out.println("   Nenhum aluno vinculado a orientadores");
            } else {
                distribuicao.forEach((prof, quant) ->
                        System.out.println("   • " + prof.getNome() + ": " + quant + " aluno(s)"));
            }

            long semOrientador = todosAlunos.stream().filter(a -> a.getOrientador() == null).count();
            if (semOrientador > 0) {
                System.out.println("\n⚠️  Alunos sem orientador: " + semOrientador);
            }
        }

        // Método para buscar alunos de um professor específico
        public void exibirAlunosPorProfessor(String nomeProfessor) {
            Professor professor = gerenciador.buscarProfessorPorNome(nomeProfessor);
            if (professor == null) {
                System.out.println("❌ Professor não encontrado: " + nomeProfessor);
                return;
            }

            System.out.println("\n🔍 **ALUNOS DO PROFESSOR: " + professor.getNome() + "**");
            System.out.println("=================================");
            System.out.println("Quantidade: " + professor.getQuantidadeAlunos() + " aluno(s)");

            if (professor.getQuantidadeAlunos() == 0) {
                System.out.println("Nenhum aluno vinculado a este professor.");
            } else {
                List<Aluno> alunos = professor.getAlunos();
                for (int i = 0; i < alunos.size(); i++) {
                    Aluno aluno = alunos.get(i);
                    System.out.println((i + 1) + ". " + aluno.getNome() + " - " + aluno.getEmail());
                }
            }
        }
    }

