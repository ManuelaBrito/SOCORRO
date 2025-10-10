package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class telacaixaController {

    // -----------------------------
    // Componentes Caixa
    // -----------------------------
    @FXML
    private TextField txf_caixaTamanho;

    @FXML
    private TextField txf_caixaCor;

    @FXML
    private TextField txf_caixaDono;

    @FXML
    private Button btn_enviar1;

    // -----------------------------
    // Componentes Homem
    // -----------------------------
    @FXML
    private TextField txf_homemAltura;

    @FXML
    private TextField txf_homemCor;

    @FXML
    private TextField txf_homemPersonalidade;

    @FXML
    private Button btn_enviar2;

    // -----------------------------
    // Componentes Casa
    // -----------------------------
    @FXML
    private TextField txf_casaLocalizacao;

    @FXML
    private TextField txf_casaCor;

    @FXML
    private TextField txf_casaTamanho;

    @FXML
    private Button btn_enviar3;

    // -----------------------------
    // Ações dos botões
    // -----------------------------
    @FXML
    void enviarDados(ActionEvent event) {
        // Cria objeto Caixa com os dados do formulário
        Caixa caixa = new Caixa(
                txf_caixaTamanho.getText(),
                txf_caixaCor.getText(),
                txf_caixaDono.getText()
        );

        // Salva em CSV
        caixa.salvar();

        // Limpa campos de texto
        txf_caixaTamanho.clear();
        txf_caixaCor.clear();
        txf_caixaDono.clear();
    }

    @FXML
    void enviarDados2(ActionEvent event) {
        // Converte altura para double, caso seja válido
        double altura = 0;
        try {
            altura = Double.parseDouble(txf_homemAltura.getText());
        } catch (NumberFormatException e) {
            System.out.println("Altura inválida!");
        }

        // Cria objeto Homem com os dados do formulário
        Homem homem = new Homem(
                altura,
                txf_homemCor.getText(),
                txf_homemPersonalidade.getText()
        );

        // Salva em CSV
        homem.salvar();

        // Limpa campos de texto
        txf_homemAltura.clear();
        txf_homemCor.clear();
        txf_homemPersonalidade.clear();
    }

    @FXML
    void enviarDados3(ActionEvent event) {
        // Cria objeto Casa com os dados do formulário
        Casa casa = new Casa(
                txf_casaLocalizacao.getText(),
                txf_casaCor.getText(),
                txf_casaTamanho.getText()
        );

        // Salva em CSV
        casa.salvar();

        // Limpa campos de texto
        txf_casaLocalizacao.clear();
        txf_casaCor.clear();
        txf_casaTamanho.clear();
    }
}
