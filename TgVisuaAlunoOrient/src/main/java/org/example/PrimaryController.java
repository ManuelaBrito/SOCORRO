package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {

    @FXML private TableView<Aluno> tableViewAlunos;
    @FXML private TableColumn<Aluno, String> colNomeAluno;
    @FXML private TableColumn<Aluno, String> colEmailAluno;
    @FXML private TableColumn<Aluno, String> colOrientadorAluno;

    @FXML private TableView<Professor> tableViewProfessores;
    @FXML private TableColumn<Professor, String> colNomeProfessor;
    @FXML private TableColumn<Professor, Integer> colQuantidadeAlunos;

    private GerenciadorOrientacoes gerenciador;
    private ObservableList<Aluno> listaAlunos;
    private ObservableList<Professor> listaProfessores;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inicializarGerenciador();
        configurarTabelas();
        carregarDados();
    }

    private void inicializarGerenciador() {
        gerenciador = new GerenciadorOrientacoes();
        adicionarDadosExemplo();
    }

    private void configurarTabelas() {
        // Configurar tabela de alunos
        colNomeAluno.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEmailAluno.setCellValueFactory(new PropertyValueFactory<>("email"));
        colOrientadorAluno.setCellValueFactory(cellData -> {
            Professor orientador = cellData.getValue().getOrientador();
            String nomeOrientador = (orientador != null) ? orientador.getNome() : "Sem orientador";
            return new javafx.beans.property.SimpleStringProperty(nomeOrientador);
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