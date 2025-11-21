package br.com.squadtech.bluetech.controller.adm;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Administrador implements Initializable {

    // Componentes FXML
    @FXML private SplitPane splitPanelMenuADM;
    @FXML private AnchorPane paneSuperiorMenuADM;
    @FXML private Label lblTituloADM;
    @FXML private ImageView imgViewFotoADM;
    @FXML private Label lblADM;
    @FXML private Label lblADMResp;
    @FXML private VBox vboxMenuADM;
    @FXML private Accordion accordionADM;
    @FXML private JFXButton btnCadastrarProfTG;
    @FXML private JFXButton btnCadastrarProfTGUni;

    // Dados do administrador
    private String nomeAdministrador;
    private String administradorResponsavel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarAccordion();
        configurarBotoes();
        aplicarEstilos();
    }

    /**
     * Configura o Accordion com as opções do menu
     */
    private void configurarAccordion() {
        // Criando TitledPanes para o Accordion
        TitledPane paneUsuarios = criarTitledPane("Gestão de Usuários",
                criarConteudoGestaoUsuarios());

        TitledPane paneRelatorios = criarTitledPane("Relatórios",
                criarConteudoRelatorios());

        TitledPane paneConfiguracoes = criarTitledPane("Configurações",
                criarConteudoConfiguracoes());

        // Adicionando ao Accordion
        accordionADM.getPanes().addAll(paneUsuarios, paneRelatorios, paneConfiguracoes);

        // Expandir o primeiro painel por padrão
        if (!accordionADM.getPanes().isEmpty()) {
            accordionADM.setExpandedPane(accordionADM.getPanes().get(0));
        }
    }

    /**
     * Cria um TitledPane com conteúdo personalizado
     */
    private TitledPane criarTitledPane(String titulo, VBox conteudo) {
        TitledPane pane = new TitledPane();
        pane.setText(titulo);
        pane.setContent(conteudo);
        pane.setExpanded(false);
        pane.getStyleClass().add("menu-titled-pane");
        return pane;
    }

    /**
     * Conteúdo para Gestão de Usuários
     */
    private VBox criarConteudoGestaoUsuarios() {
        VBox vbox = new VBox(8);
        vbox.getStyleClass().add("accordion-content");

        JFXButton btnListarUsuarios = criarBotaoAccordion("Listar Usuários",
                "#listarUsuarios", "btn-accordion");

        JFXButton btnCadastrarUsuario = criarBotaoAccordion("Cadastrar Usuário",
                "#cadastrarUsuario", "btn-accordion");

        JFXButton btnEditarPermissoes = criarBotaoAccordion("Editar Permissões",
                "#editarPermissoes", "btn-accordion");

        vbox.getChildren().addAll(btnListarUsuarios, btnCadastrarUsuario, btnEditarPermissoes);
        return vbox;
    }

    /**
     * Conteúdo para Relatórios
     */
    private VBox criarConteudoRelatorios() {
        VBox vbox = new VBox(8);
        vbox.getStyleClass().add("accordion-content");

        JFXButton btnRelatorioAcessos = criarBotaoAccordion("Relatório de Acessos",
                "#relatorioAcessos", "btn-accordion");

        JFXButton btnRelatorioAtividades = criarBotaoAccordion("Relatório de Atividades",
                "#relatorioAtividades", "btn-accordion");

        JFXButton btnEstatisticas = criarBotaoAccordion("Estatísticas do Sistema",
                "#estatisticasSistema", "btn-accordion");

        vbox.getChildren().addAll(btnRelatorioAcessos, btnRelatorioAtividades, btnEstatisticas);
        return vbox;
    }

    /**
     * Conteúdo para Configurações
     */
    private VBox criarConteudoConfiguracoes() {
        VBox vbox = new VBox(8);
        vbox.getStyleClass().add("accordion-content");

        JFXButton btnConfigSistema = criarBotaoAccordion("Configurações do Sistema",
                "#configuracoesSistema", "btn-accordion");

        JFXButton btnBackup = criarBotaoAccordion("Backup e Restauração",
                "#backupRestauracao", "btn-accordion");

        JFXButton btnLogs = criarBotaoAccordion("Visualizar Logs",
                "#visualizarLogs", "btn-accordion");

        vbox.getChildren().addAll(btnConfigSistema, btnBackup, btnLogs);
        return vbox;
    }

    /**
     * Cria botões para o Accordion
     */
    private JFXButton criarBotaoAccordion(String texto, String metodoAction, String styleClass) {
        JFXButton button = new JFXButton(texto);
        button.setMaxWidth(Double.MAX_VALUE);
        button.getStyleClass().add(styleClass);

        // Configurar ação baseada no método
        button.setOnAction(event -> {
            switch (metodoAction) {
                case "#listarUsuarios":
                    listarUsuarios();
                    break;
                case "#cadastrarUsuario":
                    cadastrarUsuario();
                    break;
                case "#editarPermissoes":
                    editarPermissoes();
                    break;
                case "#relatorioAcessos":
                    gerarRelatorioAcessos();
                    break;
                case "#relatorioAtividades":
                    gerarRelatorioAtividades();
                    break;
                case "#estatisticasSistema":
                    exibirEstatisticasSistema();
                    break;
                case "#configuracoesSistema":
                    abrirConfiguracoesSistema();
                    break;
                case "#backupRestauracao":
                    abrirBackupRestauracao();
                    break;
                case "#visualizarLogs":
                    visualizarLogs();
                    break;
            }
        });

        return button;
    }

    /**
     * Configura os botões principais
     */
    private void configurarBotoes() {
        btnCadastrarProfTG.setOnAction(event -> abrirCadastrarProfTG());
        btnCadastrarProfTGUni.setOnAction(event -> abrirCadastrarProfTGUni());
    }

    /**
     * Aplica estilos personalizados
     */
    private void aplicarEstilos() {
        // Estilos podem ser aplicados via CSS ou programaticamente
        btnCadastrarProfTG.getStyleClass().add("btn-primary");
        btnCadastrarProfTGUni.getStyleClass().add("btn-secondary");
    }

    // ========== MÉTODOS DE AÇÃO DOS BOTÕES PRINCIPAIS ==========

    @FXML
    private void abrirCadastrarProfTG() {
        System.out.println("Abrindo cadastro em massa...");
        // Implementar lógica para cadastro em massa
        // Ex: navegar para outra tela ou abrir modal
    }

    @FXML
    private void abrirCadastrarProfTGUni() {
        System.out.println("Abrindo cadastro unitário...");
        // Implementar lógica para cadastro unitário
        // Ex: navegar para outra tela ou abrir modal
    }

    // ========== MÉTODOS DO ACCORDION ==========

    private void listarUsuarios() {
        System.out.println("Listando usuários...");
        // Navegar para tela de listagem de usuários
    }

    private void cadastrarUsuario() {
        System.out.println("Cadastrando usuário...");
        // Abrir formulário de cadastro de usuário
    }

    private void editarPermissoes() {
        System.out.println("Editando permissões...");
        // Abrir tela de edição de permissões
    }

    private void gerarRelatorioAcessos() {
        System.out.println("Gerando relatório de acessos...");
        // Gerar e exibir relatório de acessos
    }

    private void gerarRelatorioAtividades() {
        System.out.println("Gerando relatório de atividades...");
        // Gerar e exibir relatório de atividades
    }

    private void exibirEstatisticasSistema() {
        System.out.println("Exibindo estatísticas do sistema...");
        // Exibir estatísticas e métricas do sistema
    }

    private void abrirConfiguracoesSistema() {
        System.out.println("Abrindo configurações do sistema...");
        // Abrir painel de configurações do sistema
    }

    private void abrirBackupRestauracao() {
        System.out.println("Abrindo backup e restauração...");
        // Abrir utilitário de backup e restauração
    }

    private void visualizarLogs() {
        System.out.println("Visualizando logs...");
        // Abrir visualizador de logs do sistema
    }

    // ========== MÉTODOS PÚBLICOS PARA CONFIGURAÇÃO ==========

    /**
     * Define os dados do administrador logado
     */
    public void setDadosAdministrador(String nome, String responsavel) {
        this.nomeAdministrador = nome;
        this.administradorResponsavel = responsavel;

        // Atualizar labels
        if (lblADM != null) {
            lblADM.setText("Administrador: " + nome);
        }
        if (lblADMResp != null) {
            lblADMResp.setText("ADMINISTRADOR RESPONSÁVEL: " + responsavel);
        }
    }

    /**
     * Define a imagem do administrador
     */
    public void setImagemAdministrador(String urlImagem) {
        try {
            javafx.scene.image.Image image = new javafx.scene.image.Image(urlImagem);
            imgViewFotoADM.setImage(image);
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem: " + e.getMessage());
            // Manter imagem padrão
        }
    }

    /**
     * Fecha a tela atual
     */
    public void fecharTela() {
        Stage stage = (Stage) splitPanelMenuADM.getScene().getWindow();
        stage.close();
    }

    /**
     * Minimiza a tela
     */
    public void minimizarTela() {
        Stage stage = (Stage) splitPanelMenuADM.getScene().getWindow();
        stage.setIconified(true);
    }

    // ========== GETTERS PARA ACESSO EXTERNO ==========

    public String getNomeAdministrador() {
        return nomeAdministrador;
    }

    public String getAdministradorResponsavel() {
        return administradorResponsavel;
    }

    public Accordion getAccordionADM() {
        return accordionADM;
    }
}