package br.com.squadtech.bluetech.controller.adm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CadastroProfessoresADMController{

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnSalvar;

    @FXML
    private TableColumn<?, ?> colCargo;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colNome;

    @FXML
    private TableView<?> tableViewProfessores;

    @FXML
    private TextField txtCargo;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void salvarProfessor(ActionEvent event) {

    }

}
