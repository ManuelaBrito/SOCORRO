package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PrimaryController {

    @FXML private Label welcomeLabel;
    @FXML private Label alunoInfoLabel;
    @FXML private TextArea feedbackTextArea;
    @FXML private Button visualizarButton;
    @FXML private ComboBox<Feedback> feedbackComboBox;

    private SistemaFeedback sistema;
    private ObservableList<Feedback> feedbacksList;

    @FXML
    public void initialize() {
        sistema = new SistemaFeedback();
        boolean loginSucesso = sistema.loginAluno("joao@email.com");

        if (loginSucesso) {
            Aluno aluno = sistema.getAlunoLogado();
            welcomeLabel.setText("Bem-vindo, " + aluno.getNome() + "!");
            alunoInfoLabel.setText("Email: " + aluno.getEmail() + " | Orientador: " + aluno.getOrientador());
            carregarFeedbacks();
        } else {
            welcomeLabel.setText("Erro no login!");
            visualizarButton.setDisable(true);
        }
    }

    private void carregarFeedbacks() {
        feedbacksList = FXCollections.observableArrayList(sistema.getFeedbacksAlunoLogado());
        feedbackComboBox.setItems(feedbacksList);

        if (!feedbacksList.isEmpty()) {
            feedbackComboBox.getSelectionModel().selectFirst();
            mostrarFeedbackSelecionado();
        } else {
            feedbackTextArea.setText("Nenhum feedback disponível.");
            visualizarButton.setDisable(true);
        }
    }

    private void mostrarFeedbackSelecionado() {
        Feedback feedbackSelecionado = feedbackComboBox.getSelectionModel().getSelectedItem();
        if (feedbackSelecionado != null) {
            String textoCompleto = feedbackSelecionado.getResumo() +
                    "\n\n--- FEEDBACK ---\n\n" +
                    feedbackSelecionado.getConteudo();
            feedbackTextArea.setText(textoCompleto);
        }
    }

    @FXML
    private void handleVisualizarFeedback() {
        Feedback feedbackSelecionado = feedbackComboBox.getSelectionModel().getSelectedItem();
        if (feedbackSelecionado != null) {
            String conteudo = sistema.getAlunoLogado().visualizarFeedback(feedbackSelecionado);
            feedbackTextArea.setText(conteudo);
        }
    }

    @FXML
    private void handleSelecionarFeedback() {
        mostrarFeedbackSelecionado();
    }

    @FXML
    private void handleAlternarAluno() {
        String emailAtual = sistema.getAlunoLogado().getEmail();
        String novoEmail = emailAtual.equals("joao@email.com") ? "maria@email.com" : "joao@email.com";

        sistema.loginAluno(novoEmail);
        Aluno aluno = sistema.getAlunoLogado();

        welcomeLabel.setText("Bem-vindo, " + aluno.getNome() + "!");
        alunoInfoLabel.setText("Email: " + aluno.getEmail() + " | Orientador: " + aluno.getOrientador());
        carregarFeedbacks();
    }
}
