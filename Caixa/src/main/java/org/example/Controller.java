package org.example;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Controller {

    @FXML
    private Button btn_enviar1;

    @FXML
    private Button btn_enviar2;

    @FXML
    private Button btn_enviar3;

    @FXML
    private Tab tpn_caixa;

    @FXML
    private Tab tpn_casa;

    @FXML
    private Tab tpn_homem;

        @FXML
        private TextField txt_tam1 , txt_cor1 , txt_dono;

        @FXML
        public void enviarDados(ActionEvent event) {

            String altura = txt_alt.getText();
            String cor = txt_cor1.getText();
            String dono = txt_dono.getText();

            // Salvar em arquivo
            try {
                String dados = "Altura " + altura + "\nCor: " + cor + "\nDono: " + dono + "\n\n";
                Files.write(Paths.get("formulario.txt"),
                        dados.getBytes(),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);

                System.out.println("Formulário enviado!");

                // Limpar campos após enviar
                limparTodosCampos();

            } catch (IOException e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }

        private void apagarTodosCampos() {
            txt_tam1.clear();
            txt_cor1.clear();
            txt_dono.clear();
        }
    @FXML
    private TextField txt_alt , txt_cor2 , txt_perso;
    @FXML
    private void enviarDados2(ActionEvent event) {

        String altura = txt_alt.getText();
        String cor = txt_cor2.getText();
        String perso = txt_perso.getText();

        // Salvar em arquivo
        try {
            String dados = "altura :" + altura + "\nCor: " + cor + "\nPerso: " + perso + "\n\n";
            Files.write(Paths.get("formulario.txt"),
                    dados.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);

            System.out.println("Formulário enviado!");

            // Limpar campos após enviar
            limparCampos();

        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    @FXML
    private void limparCampos() {
        txt_alt.clear();
        txt_cor2.clear();
        txt_perso.clear();
    }

    @FXML
    private TextField txt_loc , txt_cor3 , txt_tam2;
    @FXML
    private void enviarDados3(ActionEvent event) {
        String localizacao = txt_loc.getText();
        String cor = txt_cor3.getText();
        String tamanho = txt_tam2.getText();

        // Salvar em arquivo
        try {
            String dados = "localizacao :" + localizacao + "\nCor: " + cor + "\nTamanho: " + tamanho + "\n\n";
            Files.write(Paths.get("formulario.txt"),
                    dados.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);

            System.out.println("Formulário enviado!");

            // Limpar campos após enviar
            limparCampos();

        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    @FXML
    private void limparTodosCampos() {
        txt_loc.clear();
        txt_cor3.clear();
        txt_tam2.clear();
    }

    public void salvarDados(MouseEvent mouseEvent) {
    }
}



