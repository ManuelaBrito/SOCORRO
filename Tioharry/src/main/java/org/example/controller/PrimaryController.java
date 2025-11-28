package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.DAO.DogDAO;
import org.example.DAO.PlantaDAO;
import org.example.DAO.SitioDAO;
import org.example.model.Dog;
import org.example.model.Planta;
import org.example.model.Sitio;

import java.util.List;

public class PrimaryController {

    @FXML private Tab tap_sitio;
    @FXML private AnchorPane acp_sitio;
    @FXML private TextField txt_tam1;
    @FXML private TextField txt_duende;
    @FXML private TextField txt_cogu;
    @FXML private Button btn_salvar1;
    @FXML private Button btn_excluir1;
    @FXML private Button btn_editar1;
    @FXML private TableView<Sitio> Table_sítio;
    @FXML private TableColumn<Sitio, Integer> col_sitio_id;
    @FXML private TableColumn<Sitio, String> col_sitio_tamanho;
    @FXML private TableColumn<Sitio, String> col_sitio_duende;
    @FXML private TableColumn<Sitio, String> col_sitio_cogumelo;

    @FXML private Tab tap_planta;
    @FXML private AnchorPane acp_planta;
    @FXML private TextField txt_tam2;
    @FXML private TextField txt_cor1;
    @FXML private TextField txt_espe;
    @FXML private Button btn_salvar2;
    @FXML private Button btn_excluir2;
    @FXML private Button btn_editar2;
    @FXML private TableView<Planta> table_planta_tabela;
    @FXML private TableColumn<Planta, Integer> col_planta_id;
    @FXML private TableColumn<Planta, String> col_planta_tamanho;
    @FXML private TableColumn<Planta, String> col_planta_cor;
    @FXML private TableColumn<Planta, String> col_planta_especie;

    @FXML private Tab tap_planta1;
    @FXML private AnchorPane acp_dog;
    @FXML private TextField txt_nome;
    @FXML private TextField txt_cor3;
    @FXML private TextField txt_idioma;
    @FXML private Button btn_salvar3;
    @FXML private Button btn_excluir3;
    @FXML private Button btn_editar3;
    @FXML private TableView<Dog> table_dog_tabela;
    @FXML private TableColumn<Dog, Integer> col_dog_id;
    @FXML private TableColumn<Dog, String> col_dog_nome;
    @FXML private TableColumn<Dog, String> col_dog_cor;
    @FXML private TableColumn<Dog, String> col_dog_idioma;

    private DogDAO dogDAO = new DogDAO();
    private PlantaDAO plantaDAO = new PlantaDAO();
    private SitioDAO sitioDAO = new SitioDAO();

    private ObservableList<Dog> dogsList = FXCollections.observableArrayList();
    private ObservableList<Planta> plantasList = FXCollections.observableArrayList();
    private ObservableList<Sitio> sitiosList = FXCollections.observableArrayList();

    private Sitio sitioSelecionado;
    private Planta plantaSelecionada;
    private Dog dogSelecionado;

    // Modo de edição
    private boolean editandoSitio = false;
    private boolean editandoPlanta = false;
    private boolean editandoDog = false;

    @FXML
    public void initialize() {
        System.out.println("=== INICIALIZANDO PRIMARY CONTROLLER ===");

        try {
            // CRIAR TABELAS SE NÃO EXISTIREM - MÉTODO ROBUSTO
            System.out.println("1. Criando tabela sitio...");
            sitioDAO.criarTabelaSeNaoExistir();

            System.out.println("2. Criando tabela plantas...");
            plantaDAO.criarTabelaPlantasForcadamente(); // Usa o método robusto

            System.out.println("3. Criando tabela dogs...");
            dogDAO.criarTabelaSeNaoExistir();

            // Verificar se as tabelas existem antes de continuar
            System.out.println("4. Verificando tabelas...");
            verificarTabelas();

        } catch (Exception e) {
            System.out.println("⚠️  Erro durante criação de tabelas: " + e.getMessage());
            System.out.println("⚠️  Continuando com a aplicação mesmo com erro...");
        }

        try {
            configurarTabelas();
            carregarDados();
            configurarSelecoes();
            configurarBotoesEdicao();

            System.out.println("✅ PrimaryController inicializado com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro durante inicialização: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para verificar todas as tabelas
    private void verificarTabelas() {
        System.out.println("=== VERIFICAÇÃO DE TABELAS ===");
        System.out.println("Tabela sitio existe: " + sitioDAO.tabelaExiste());
        System.out.println("Tabela plantas existe: " + plantaDAO.tabelaExiste());
        System.out.println("Tabela dogs existe: " + dogDAO.tabelaExiste());
        System.out.println("==============================");
    }

    private void configurarTabelas() {
        try {
            col_sitio_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_sitio_tamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
            col_sitio_duende.setCellValueFactory(new PropertyValueFactory<>("duende"));
            col_sitio_cogumelo.setCellValueFactory(new PropertyValueFactory<>("cogumelo"));
            Table_sítio.setItems(sitiosList);

            col_planta_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_planta_tamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
            col_planta_cor.setCellValueFactory(new PropertyValueFactory<>("cor"));
            col_planta_especie.setCellValueFactory(new PropertyValueFactory<>("especie"));
            table_planta_tabela.setItems(plantasList);

            col_dog_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_dog_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            col_dog_cor.setCellValueFactory(new PropertyValueFactory<>("cor"));
            col_dog_idioma.setCellValueFactory(new PropertyValueFactory<>("idioma"));
            table_dog_tabela.setItems(dogsList);

            System.out.println("✅ Tabelas configuradas com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao configurar tabelas: " + e.getMessage());
        }
    }

    private void configurarSelecoes() {
        try {
            Table_sítio.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldSelection, newSelection) -> {
                        sitioSelecionado = newSelection;
                        btn_excluir1.setDisable(newSelection == null);
                        btn_editar1.setDisable(newSelection == null);
                        if (newSelection != null) {
                            preencherCamposSitio(newSelection);
                        }
                    }
            );

            table_planta_tabela.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldSelection, newSelection) -> {
                        plantaSelecionada = newSelection;
                        btn_excluir2.setDisable(newSelection == null);
                        btn_editar2.setDisable(newSelection == null);
                        if (newSelection != null) {
                            preencherCamposPlanta(newSelection);
                        }
                    }
            );

            table_dog_tabela.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldSelection, newSelection) -> {
                        dogSelecionado = newSelection;
                        btn_excluir3.setDisable(newSelection == null);
                        btn_editar3.setDisable(newSelection == null);
                        if (newSelection != null) {
                            preencherCamposDog(newSelection);
                        }
                    }
            );

            btn_excluir1.setDisable(true);
            btn_editar1.setDisable(true);
            btn_excluir2.setDisable(true);
            btn_editar2.setDisable(true);
            btn_excluir3.setDisable(true);
            btn_editar3.setDisable(true);

            System.out.println("✅ Seleções configuradas com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao configurar seleções: " + e.getMessage());
        }
    }

    private void configurarBotoesEdicao() {
        try {
            // Configurar botões de edição
            btn_editar1.setOnAction(e -> iniciarEdicaoSitio());
            btn_editar2.setOnAction(e -> iniciarEdicaoPlanta());
            btn_editar3.setOnAction(e -> iniciarEdicaoDog());

            System.out.println("✅ Botões de edição configurados com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao configurar botões de edição: " + e.getMessage());
        }
    }

    // Métodos para iniciar edição
    private void iniciarEdicaoSitio() {
        if (sitioSelecionado != null) {
            editandoSitio = true;
            btn_salvar1.setText("Atualizar");
            btn_editar1.setDisable(true);
            mostrarAlerta("Edição", "Modifique os dados do sítio e clique em 'Atualizar'", Alert.AlertType.INFORMATION);
        }
    }

    private void iniciarEdicaoPlanta() {
        if (plantaSelecionada != null) {
            editandoPlanta = true;
            btn_salvar2.setText("Atualizar");
            btn_editar2.setDisable(true);
            mostrarAlerta("Edição", "Modifique os dados da planta e clique em 'Atualizar'", Alert.AlertType.INFORMATION);
        }
    }

    private void iniciarEdicaoDog() {
        if (dogSelecionado != null) {
            editandoDog = true;
            btn_salvar3.setText("Atualizar");
            btn_editar3.setDisable(true);
            mostrarAlerta("Edição", "Modifique os dados do cachorro e clique em 'Atualizar'", Alert.AlertType.INFORMATION);
        }
    }

    // Métodos para salvar/atualizar
    @FXML
    private void salvarDados1() {
        try {
            if (editandoSitio) {
                atualizarSitio();
            } else {
                salvarSitio();
            }
        } catch (Exception e) {
            mostrarAlerta("Erro", "Erro ao salvar sítio: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void salvarDados2() {
        try {
            if (editandoPlanta) {
                atualizarPlanta();
            } else {
                salvarPlanta();
            }
        } catch (Exception e) {
            mostrarAlerta("Erro", "Erro ao salvar planta: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void salvarDados3() {
        try {
            if (editandoDog) {
                atualizarDog();
            } else {
                salvarDog();
            }
        } catch (Exception e) {
            mostrarAlerta("Erro", "Erro ao salvar cachorro: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void salvarSitio() {
        if (validarCamposSitio()) {
            Sitio sitio = new Sitio(
                    txt_tam1.getText(),
                    txt_duende.getText(),
                    txt_cogu.getText()
            );

            sitioDAO.inserirSitio(sitio);
            carregarSitios();
            limparCamposSitio();
            mostrarAlerta("Sucesso", "Sítio salvo com sucesso!", Alert.AlertType.INFORMATION);
        }
    }

    private void salvarPlanta() {
        if (validarCamposPlanta()) {
            Planta planta = new Planta(
                    txt_tam2.getText(),
                    txt_cor1.getText(),
                    txt_espe.getText()
            );

            plantaDAO.inserirPlanta(planta);
            carregarPlantas();
            limparCamposPlanta();
            mostrarAlerta("Sucesso", "Planta salva com sucesso!", Alert.AlertType.INFORMATION);
        }
    }

    private void salvarDog() {
        if (validarCamposDog()) {
            Dog dog = new Dog(
                    txt_nome.getText(),
                    txt_cor3.getText(),
                    txt_idioma.getText()
            );

            dogDAO.inserirDog(dog);
            carregarDogs();
            limparCamposDog();
            mostrarAlerta("Sucesso", "Cachorro salvo com sucesso!", Alert.AlertType.INFORMATION);
        }
    }

    // Métodos para atualizar
    private void atualizarSitio() {
        if (validarCamposSitio() && sitioSelecionado != null) {
            // Cria um novo objeto com os dados atualizados
            Sitio sitioAtualizado = new Sitio(
                    sitioSelecionado.getId(), // Mantém o ID original
                    txt_tam1.getText(),
                    txt_duende.getText(),
                    txt_cogu.getText()
            );

            sitioDAO.atualizarSitio(sitioAtualizado);
            carregarSitios();
            limparCamposSitio();
            finalizarEdicaoSitio();
            mostrarAlerta("Sucesso", "Sítio atualizado com sucesso!", Alert.AlertType.INFORMATION);
        }
    }

    private void atualizarPlanta() {
        if (validarCamposPlanta() && plantaSelecionada != null) {
            Planta plantaAtualizada = new Planta(
                    plantaSelecionada.getId(), // Mantém o ID original
                    txt_tam2.getText(),
                    txt_cor1.getText(),
                    txt_espe.getText()
            );

            plantaDAO.atualizarPlanta(plantaAtualizada);
            carregarPlantas();
            limparCamposPlanta();
            finalizarEdicaoPlanta();
            mostrarAlerta("Sucesso", "Planta atualizada com sucesso!", Alert.AlertType.INFORMATION);
        }
    }

    private void atualizarDog() {
        if (validarCamposDog() && dogSelecionado != null) {
            Dog dogAtualizado = new Dog(
                    dogSelecionado.getId(), // Mantém o ID original
                    txt_nome.getText(),
                    txt_cor3.getText(),
                    txt_idioma.getText()
            );

            dogDAO.atualizarDog(dogAtualizado);
            carregarDogs();
            limparCamposDog();
            finalizarEdicaoDog();
            mostrarAlerta("Sucesso", "Cachorro atualizado com sucesso!", Alert.AlertType.INFORMATION);
        }
    }

    // Métodos para finalizar edição
    private void finalizarEdicaoSitio() {
        editandoSitio = false;
        btn_salvar1.setText("Salvar");
        btn_editar1.setDisable(sitioSelecionado == null);
    }

    private void finalizarEdicaoPlanta() {
        editandoPlanta = false;
        btn_salvar2.setText("Salvar");
        btn_editar2.setDisable(plantaSelecionada == null);
    }

    private void finalizarEdicaoDog() {
        editandoDog = false;
        btn_salvar3.setText("Salvar");
        btn_editar3.setDisable(dogSelecionado == null);
    }

    // Métodos de exclusão (mantidos)
    @FXML
    private void excluirDados1() {
        excluirSitio();
    }

    @FXML
    private void excluirDados2() {
        excluirPlanta();
    }

    @FXML
    private void excluirDados3() {
        excluirDog();
    }

    private void excluirSitio() {
        if (sitioSelecionado != null) {
            Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacao.setTitle("Confirmação");
            confirmacao.setHeaderText("Excluir Sítio");
            confirmacao.setContentText("Tem certeza que deseja excluir este sítio?");

            if (confirmacao.showAndWait().get() == ButtonType.OK) {
                try {
                    sitioDAO.excluirSitio(sitioSelecionado.getId());
                    carregarSitios();
                    limparCamposSitio();
                    finalizarEdicaoSitio();
                    mostrarAlerta("Sucesso", "Sítio excluído com sucesso!", Alert.AlertType.INFORMATION);
                } catch (Exception e) {
                    mostrarAlerta("Erro", "Erro ao excluir sítio: " + e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        } else {
            mostrarAlerta("Aviso", "Selecione um sítio para excluir!", Alert.AlertType.WARNING);
        }
    }

    private void excluirPlanta() {
        if (plantaSelecionada != null) {
            Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacao.setTitle("Confirmação");
            confirmacao.setHeaderText("Excluir Planta");
            confirmacao.setContentText("Tem certeza que deseja excluir esta planta?");

            if (confirmacao.showAndWait().get() == ButtonType.OK) {
                try {
                    plantaDAO.excluirPlanta(plantaSelecionada.getId());
                    carregarPlantas();
                    limparCamposPlanta();
                    finalizarEdicaoPlanta();
                    mostrarAlerta("Sucesso", "Planta excluída com sucesso!", Alert.AlertType.INFORMATION);
                } catch (Exception e) {
                    mostrarAlerta("Erro", "Erro ao excluir planta: " + e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        } else {
            mostrarAlerta("Aviso", "Selecione uma planta para excluir!", Alert.AlertType.WARNING);
        }
    }

    private void excluirDog() {
        if (dogSelecionado != null) {
            Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacao.setTitle("Confirmação");
            confirmacao.setHeaderText("Excluir Cachorro");
            confirmacao.setContentText("Tem certeza que deseja excluir " + dogSelecionado.getNome() + "?");

            if (confirmacao.showAndWait().get() == ButtonType.OK) {
                try {
                    dogDAO.excluirDog(dogSelecionado.getId());
                    carregarDogs();
                    limparCamposDog();
                    finalizarEdicaoDog();
                    mostrarAlerta("Sucesso", "Cachorro excluído com sucesso!", Alert.AlertType.INFORMATION);
                } catch (Exception e) {
                    mostrarAlerta("Erro", "Erro ao excluir cachorro: " + e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        } else {
            mostrarAlerta("Aviso", "Selecione um cachorro para excluir!", Alert.AlertType.WARNING);
        }
    }

    // Métodos de validação (mantidos)
    private boolean validarCamposSitio() {
        if (txt_tam1.getText().isEmpty() || txt_duende.getText().isEmpty() || txt_cogu.getText().isEmpty()) {
            mostrarAlerta("Erro", "Preencha todos os campos do sítio!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private boolean validarCamposPlanta() {
        if (txt_tam2.getText().isEmpty() || txt_cor1.getText().isEmpty() || txt_espe.getText().isEmpty()) {
            mostrarAlerta("Erro", "Preencha todos os campos da planta!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private boolean validarCamposDog() {
        if (txt_nome.getText().isEmpty() || txt_cor3.getText().isEmpty() || txt_idioma.getText().isEmpty()) {
            mostrarAlerta("Erro", "Preencha todos os campos do cachorro!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    // Métodos de limpeza (atualizados para finalizar edição)
    private void limparCamposSitio() {
        txt_tam1.clear();
        txt_duende.clear();
        txt_cogu.clear();
        sitioSelecionado = null;
        Table_sítio.getSelectionModel().clearSelection();
        btn_excluir1.setDisable(true);
        btn_editar1.setDisable(true);
        finalizarEdicaoSitio();
    }

    private void limparCamposPlanta() {
        txt_tam2.clear();
        txt_cor1.clear();
        txt_espe.clear();
        plantaSelecionada = null;
        table_planta_tabela.getSelectionModel().clearSelection();
        btn_excluir2.setDisable(true);
        btn_editar2.setDisable(true);
        finalizarEdicaoPlanta();
    }

    private void limparCamposDog() {
        txt_nome.clear();
        txt_cor3.clear();
        txt_idioma.clear();
        dogSelecionado = null;
        table_dog_tabela.getSelectionModel().clearSelection();
        btn_excluir3.setDisable(true);
        btn_editar3.setDisable(true);
        finalizarEdicaoDog();
    }

    // Métodos de preenchimento (mantidos)
    private void preencherCamposSitio(Sitio sitio) {
        txt_tam1.setText(sitio.getTamanho());
        txt_duende.setText(sitio.getDuende());
        txt_cogu.setText(sitio.getCogumelo());
    }

    private void preencherCamposPlanta(Planta planta) {
        txt_tam2.setText(planta.getTamanho());
        txt_cor1.setText(planta.getCor());
        txt_espe.setText(planta.getEspecie());
    }

    private void preencherCamposDog(Dog dog) {
        txt_nome.setText(dog.getNome());
        txt_cor3.setText(dog.getCor());
        txt_idioma.setText(dog.getIdioma());
    }

    // Métodos de carregamento (melhorados com tratamento de erro)
    private void carregarDados() {
        carregarSitios();
        carregarPlantas();
        carregarDogs();
    }

    private void carregarSitios() {
        try {
            sitiosList.clear();
            List<Sitio> sitios = sitioDAO.listarSitios();
            if (sitios != null) {
                sitiosList.addAll(sitios);
                System.out.println("✅ Carregados " + sitios.size() + " sítios");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao carregar sítios: " + e.getMessage());
        }
    }

    private void carregarPlantas() {
        try {
            plantasList.clear();
            List<Planta> plantas = plantaDAO.listarPlantas();
            if (plantas != null) {
                plantasList.addAll(plantas);
                System.out.println("✅ Carregadas " + plantas.size() + " plantas");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao carregar plantas: " + e.getMessage());
        }
    }

    private void carregarDogs() {
        try {
            dogsList.clear();
            List<Dog> dogs = dogDAO.listarDogs();
            if (dogs != null) {
                dogsList.addAll(dogs);
                System.out.println("✅ Carregados " + dogs.size() + " cachorros");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao carregar cachorros: " + e.getMessage());
        }
    }

    // Método de alerta (mantido)
    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    // Métodos de limpeza (mantidos)
    @FXML
    private void limparDados1() {
        limparCamposSitio();
    }

    @FXML
    private void limparDados2() {
        limparCamposPlanta();
    }

    @FXML
    private void limparDados3() {
        limparCamposDog();
    }

    // Métodos de edição para o FXML
    @FXML
    private void editarSitio() {
        iniciarEdicaoSitio();
    }

    @FXML
    private void editarPlanta() {
        iniciarEdicaoPlanta();
    }

    @FXML
    private void editarDog() {
        iniciarEdicaoDog();
    }
}