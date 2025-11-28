package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.DAO.DogDAO;
import org.example.model.Dog;

public class DogController {

    @FXML private TextField txt_nome;
    @FXML private TextField txt_cor;
    @FXML private TextField txt_idioma;
    @FXML private Button btn_salvar;
    @FXML private Button btn_editar;
    @FXML private TableView<Dog> table_dog;
    @FXML private TableColumn<Dog, Integer> col_id;
    @FXML private TableColumn<Dog, String> col_nome;
    @FXML private TableColumn<Dog, String> col_cor;
    @FXML private TableColumn<Dog, String> col_idioma;

    private DogDAO dogDAO = new DogDAO();
    private Dog dogSelecionado;
    private boolean editando = false;

    @FXML
    public void initialize() {
        configurarTabela();
        carregarDados();
    }

    private void configurarTabela() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_cor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        col_idioma.setCellValueFactory(new PropertyValueFactory<>("idioma"));

        table_dog.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    dogSelecionado = newSelection;
                    btn_editar.setDisable(newSelection == null);
                    if (newSelection != null) {
                        preencherCampos(newSelection);
                    }
                }
        );
    }

    private void carregarDados() {
        table_dog.getItems().clear();
        table_dog.getItems().addAll(dogDAO.listarDogs());
    }

    private void preencherCampos(Dog dog) {
        txt_nome.setText(dog.getNome());
        txt_cor.setText(dog.getCor());
        txt_idioma.setText(dog.getIdioma());
    }

    @FXML
    private void salvarDog() {
        if (editando) {
            atualizarDog();
        } else {
            inserirDog();
        }
    }

    private void inserirDog() {
        if (validarCampos()) {
            Dog dog = new Dog(
                    txt_nome.getText(),
                    txt_cor.getText(),
                    txt_idioma.getText()
            );
            dogDAO.inserirDog(dog);
            carregarDados();
            limparCampos();
            mostrarAlerta("Sucesso", "Cachorro salvo com sucesso!", Alert.AlertType.INFORMATION);
        }
    }

    // CORREÇÃO: Método atualizado para usar a nova assinatura
    private void atualizarDog() {
        if (validarCampos() && dogSelecionado != null) {
            // Atualiza os dados do objeto selecionado
            dogSelecionado.setNome(txt_nome.getText());
            dogSelecionado.setCor(txt_cor.getText());
            dogSelecionado.setIdioma(txt_idioma.getText());

            // CORREÇÃO: Agora passa apenas o objeto Dog
            dogDAO.atualizarDog(dogSelecionado);

            carregarDados();
            limparCampos();
            finalizarEdicao();
            mostrarAlerta("Sucesso", "Cachorro atualizado com sucesso!", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void editarDog() {
        if (dogSelecionado != null) {
            editando = true;
            btn_salvar.setText("Atualizar");
            btn_editar.setDisable(true);
        }
    }

    @FXML
    private void excluirDog() {
        if (dogSelecionado != null) {
            Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacao.setTitle("Confirmação");
            confirmacao.setHeaderText("Excluir Cachorro");
            confirmacao.setContentText("Tem certeza que deseja excluir " + dogSelecionado.getNome() + "?");

            if (confirmacao.showAndWait().get() == ButtonType.OK) {
                // CORREÇÃO: Usa o método que recebe ID
                dogDAO.excluirDog(dogSelecionado.getId());
                carregarDados();
                limparCampos();
                finalizarEdicao();
                mostrarAlerta("Sucesso", "Cachorro excluído com sucesso!", Alert.AlertType.INFORMATION);
            }
        }
    }

    @FXML
    private void limparCampos() {
        txt_nome.clear();
        txt_cor.clear();
        txt_idioma.clear();
        dogSelecionado = null;
        table_dog.getSelectionModel().clearSelection();
        finalizarEdicao();
    }

    private void finalizarEdicao() {
        editando = false;
        btn_salvar.setText("Salvar");
        btn_editar.setDisable(true);
    }

    private boolean validarCampos() {
        if (txt_nome.getText().isEmpty() || txt_cor.getText().isEmpty() || txt_idioma.getText().isEmpty()) {
            mostrarAlerta("Erro", "Preencha todos os campos!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}