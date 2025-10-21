package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class PrimaryController {

    // AnchorPanes
    @FXML private AnchorPane acp_sitio, acp_planta, acp_dog;

    // TextFields - Sítio
    @FXML private TextField txt_tam1, txt_duende, txt_cogu;

    // TextFields - Planta
    @FXML private TextField txt_tam2, txt_cor1, txt_espe;

    // TextFields - Dog
    @FXML private TextField txt_nome, txt_cor3, txt_idioma;

    // Buttons
    @FXML private Button btn_salvar1, btn_salvar2, btn_salvar3;

    // Objetos
    private Sitio sitio;
    private Planta planta;
    private Dog dog;

    @FXML
    private void salvarSitio() {
        try {
            String tamanho = txt_tam1.getText();
            String duende = txt_duende.getText();
            String cogumelo = txt_cogu.getText();

            if (tamanho.isEmpty() || duende.isEmpty() || cogumelo.isEmpty()) {
                mostrarAlerta("Erro", "Preencha todos os campos do Sítio!");
                return;
            }

            sitio = new Sitio(tamanho, duende, cogumelo);
            mostrarAlerta("Sucesso", "Sítio salvo com sucesso!\n" +
                    "Tamanho: " + sitio.getTamanho() + "\n" +
                    "Duende: " + sitio.getDuende() + "\n" +
                    "Cogumelo: " + sitio.getCogumelo());

        } catch (Exception e) {
            mostrarAlerta("Erro", "Erro ao salvar Sítio: " + e.getMessage());
        }
    }

    @FXML
    private void salvarPlanta() {
        try {
            String tamanho = txt_tam2.getText();
            String cor = txt_cor1.getText();
            String especie = txt_espe.getText();

            if (tamanho.isEmpty() || cor.isEmpty() || especie.isEmpty()) {
                mostrarAlerta("Erro", "Preencha todos os campos da Planta!");
                return;
            }

            planta = new Planta(tamanho, cor, especie);
            mostrarAlerta("Sucesso", "Planta salva com sucesso!\n" +
                    "Tamanho: " + planta.getTamanho() + "\n" +
                    "Cor: " + planta.getCor() + "\n" +
                    "Espécie: " + planta.getEspecie());

        } catch (Exception e) {
            mostrarAlerta("Erro", "Erro ao salvar Planta: " + e.getMessage());
        }
    }

    @FXML
    private void salvarCachorro() {
        try {
            String nome = txt_nome.getText();
            String cor = txt_cor3.getText();
            String idioma = txt_idioma.getText();

            if (nome.isEmpty() || cor.isEmpty() || idioma.isEmpty()) {
                mostrarAlerta("Erro", "Preencha todos os campos do Dog!");
                return;
            }

            dog = new Dog(nome, cor, idioma);
            mostrarAlerta("Sucesso", "Dog salvo com sucesso!\n" +
                    "Nome: " + dog.getNome() + "\n" +
                    "Cor: " + dog.getCor() + "\n" +
                    "Idioma: " + dog.getIdioma());

        } catch (Exception e) {
            mostrarAlerta("Erro", "Erro ao salvar Dog: " + e.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    private void initialize() {
        System.out.println("Controller inicializado!");
    }
}