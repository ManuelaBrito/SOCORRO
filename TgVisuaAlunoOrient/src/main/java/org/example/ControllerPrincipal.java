package org.example;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

    public class ControllerPrincipal implements Initializable {

        @FXML private TableView<Aluno> tableViewAlunos;
        @FXML private TableColumn<Aluno, String> colNomeAluno;
        @FXML private TableColumn<Aluno, String> colEmailAluno;
        @FXML private TableColumn<Aluno, String> colOrientadorAluno;

        @FXML private TableView<Professor> tableViewProfessores;
        @FXML private TableColumn<Professor, String> colNomeProfessor;
        @FXML private TableColumn<Professor, Integer> colQuantidadeAlunos;

        @FXML private ComboBox<Professor> comboProfessores;
        @FXML private ComboBox<Aluno> comboAlunos;
        @FXML private TextArea areaRelatorio;

        @FXML private Label labelTotalAlunos;
        @FXML private Label labelAlunosComOrientador;
        @FXML private Label labelAlunosSemOrientador;
        @FXML private Label labelMediaPorProfessor;

        private GerenciadorOrientacoes gerenciador;
        private VisualizacaoDistribuicaoTG visualizacaoTG;
        private ObservableList<Aluno> listaAlunos;
        private ObservableList<Professor> listaProfessores;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            inicializarGerenciador();
            configurarTabelas();
            carregarDados();
            atualizarEstatisticas();
        }

        private void inicializarGerenciador() {
            gerenciador = new GerenciadorOrientacoes();
            visualizacaoTG = new VisualizacaoDistribuicaoTG(gerenciador);

            // Adicionar dados de exemplo
            adicionarDadosExemplo();
        }

        private void configurarTabelas() {
            // Configurar tabela de alunos
            colNomeAluno.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colEmailAluno.setCellValueFactory(new PropertyValueFactory<>("email"));
            colOrientadorAluno.setCellValueFactory(cellData -> {
                Professor orientador = cellData.getValue().getOrientador();
                return javafx.beans.binding.Bindings.createStringBinding(
                        () -> orientador != null ? orientador.getNome() : "Sem orientador"
                );
            });

            // Configurar tabela de professores
            colNomeProfessor.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colQuantidadeAlunos.setCellValueFactory(new PropertyValueFactory<>("quantidadeAlunos"));

            // Inicializar listas observáveis
            listaAlunos = FXCollections.observableArrayList();
            listaProfessores = FXCollections.observableArrayList();

            tableViewAlunos.setItems(listaAlunos);
            tableViewProfessores.setItems(listaProfessores);
        }

        private void carregarDados() {
            listaAlunos.setAll(gerenciador.getAlunos());
            listaProfessores.setAll(gerenciador.getProfessores());

            comboAlunos.setItems(FXCollections.observableArrayList(gerenciador.getAlunos()));
            comboProfessores.setItems(FXCollections.observableArrayList(gerenciador.getProfessores()));
        }

        @FXML
        private void vincularAlunoOrientador() {
            Aluno aluno = comboAlunos.getValue();
            Professor professor = comboProfessores.getValue();

            if (aluno == null || professor == null) {
                mostrarAlerta("Selecione um aluno e um professor!");
                return;
            }

            gerenciador.vincularAlunoOrientador(aluno, professor);
            atualizarInterface();
            mostrarAlerta("Aluno vinculado com sucesso!", Alert.AlertType.INFORMATION);
        }

        @FXML
        private void desvincularAluno() {
            Aluno aluno = comboAlunos.getValue();
            if (aluno == null) {
                mostrarAlerta("Selecione um aluno!");
                return;
            }

            gerenciador.desvincularAlunoOrientador(aluno);
            atualizarInterface();
            mostrarAlerta("Aluno desvinculado com sucesso!", Alert.AlertType.INFORMATION);
        }

        @FXML
        private void gerarRelatorioCompleto() {
            StringBuilder relatorio = new StringBuilder();
            relatorio.append("RELATÓRIO COMPLETO DE ORIENTAÇÕES\n");
            relatorio.append("==================================\n\n");

            for (Professor professor : gerenciador.getProfessores()) {
                relatorio.append("Professor: ").append(professor.getNome()).append("\n");
                relatorio.append("Quantidade de alunos: ").append(professor.getQuantidadeAlunos()).append("\n");

                if (professor.getQuantidadeAlunos() > 0) {
                    relatorio.append("Alunos orientados:\n");
                    for (Aluno aluno : professor.getAlunos()) {
                        relatorio.append("  • ").append(aluno.getNome()).append(" - ").append(aluno.getEmail()).append("\n");
                    }
                } else {
                    relatorio.append("  Nenhum aluno vinculado\n");
                }
                relatorio.append("----------------------------------\n");
            }

            areaRelatorio.setText(relatorio.toString());
        }

        @FXML
        private void gerarRelatorioEstatistico() {
            StringBuilder relatorio = new StringBuilder();
            relatorio.append("RELATÓRIO ESTATÍSTICO\n");
            relatorio.append("=====================\n\n");

            int totalAlunos = gerenciador.getAlunos().size();
            int alunosComOrientador = totalAlunos - gerenciador.getAlunosSemOrientador().size();

            relatorio.append("Total de alunos: ").append(totalAlunos).append("\n");
            relatorio.append("Alunos com orientador: ").append(alunosComOrientador).append("\n");
            relatorio.append("Alunos sem orientador: ").append(gerenciador.getAlunosSemOrientador().size()).append("\n");

            if (!gerenciador.getProfessores().isEmpty()) {
                double media = (double) alunosComOrientador / gerenciador.getProfessores().size();
                relatorio.append(String.format("Média por professor: %.1f\n", media));
            }

            areaRelatorio.setText(relatorio.toString());
        }

        @FXML
        private void limparRelatorio() {
            areaRelatorio.clear();
        }

        private void atualizarInterface() {
            carregarDados();
            atualizarEstatisticas();
        }

        private void atualizarEstatisticas() {
            int totalAlunos = gerenciador.getAlunos().size();
            int alunosSemOrientador = gerenciador.getAlunosSemOrientador().size();
            int alunosComOrientador = totalAlunos - alunosSemOrientador;

            labelTotalAlunos.setText(String.valueOf(totalAlunos));
            labelAlunosComOrientador.setText(String.valueOf(alunosComOrientador));
            labelAlunosSemOrientador.setText(String.valueOf(alunosSemOrientador));

            if (!gerenciador.getProfessores().isEmpty()) {
                double media = (double) alunosComOrientador / gerenciador.getProfessores().size();
                labelMediaPorProfessor.setText(String.format("%.1f", media));
            } else {
                labelMediaPorProfessor.setText("0.0");
            }
        }

        private void mostrarAlerta(String mensagem) {
            mostrarAlerta(mensagem, Alert.AlertType.WARNING);
        }

        private void mostrarAlerta(String mensagem, Alert.AlertType tipo) {
            Alert alert = new Alert(tipo);
            alert.setTitle("Sistema de Orientação");
            alert.setHeaderText(null);
            alert.setContentText(mensagem);
            alert.showAndWait();
        }

        private void adicionarDadosExemplo() {
            // Criar professores
            Professor prof1 = new Professor("Dr. Carlos Silva");
            Professor prof2 = new Professor("Dra. Ana Santos");
            Professor prof3 = new Professor("Dr. Paulo Oliveira");

            gerenciador.adicionarProfessor(prof1);
            gerenciador.adicionarProfessor(prof2);
            gerenciador.adicionarProfessor(prof3);

            // Criar e vincular alunos
            Aluno aluno1 = new Aluno("João Pereira", "joao@email.com", prof1);
            Aluno aluno2 = new Aluno("Maria Souza", "maria@email.com", prof1);
            Aluno aluno3 = new Aluno("Pedro Costa", "pedro@email.com", prof2);
            Aluno aluno4 = new Aluno("Ana Lima", "ana@email.com", prof2);
            Aluno aluno5 = new Aluno("Carlos Rocha", "carlos@email.com", prof3);
            Aluno aluno6 = new Aluno("Julia Mendes", "julia@email.com");
            Aluno aluno7 = new Aluno("Lucas Oliveira", "lucas@email.com");

            gerenciador.adicionarAluno(aluno1);
            gerenciador.adicionarAluno(aluno2);
            gerenciador.adicionarAluno(aluno3);
            gerenciador.adicionarAluno(aluno4);
            gerenciador.adicionarAluno(aluno5);
            gerenciador.adicionarAluno(aluno6);
            gerenciador.adicionarAluno(aluno7);
        }
    }

