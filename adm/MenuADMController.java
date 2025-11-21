package br.com.squadtech.bluetech.controller.adm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MenuADMController {

    private static final Logger log = LoggerFactory.getLogger(MenuADMController.class);

    // Referência para o controller principal (similar ao painelPrincipalController do exemplo)
    private PainelPrincipalController painelPrincipalController;

    @FXML
    private Accordion accordionADM;

    @FXML
    private Button btnCadastrarProfTG;

    @FXML
    private Button btnCadastrarProfTGUni;

    @FXML
    private ImageView imgViewFotoADM;

    @FXML
    private Label lblADM;

    @FXML
    private Label lblADMResp;

    @FXML
    private Label lblTituloADM;

    @FXML
    private AnchorPane paneSuperiorMenuADM;

    @FXML
    private SplitPane splitPanelMenuADM;

    @FXML
    private VBox vboxMenuADM;

    // Método para injetar o controller principal
    public void setPainelPrincipalController(PainelPrincipalController controller) {
        this.painelPrincipalController = controller;
    }

    @FXML
    void abrirCadastrarProfTG(ActionEvent event) {
        if (painelPrincipalController != null) {
            try {
                String fxmlPath = "/fxml/adm/CadastroProfessoresADM.fxml";
                painelPrincipalController.loadContent(fxmlPath);
            } catch (IOException e) {
                log.error("Falha ao carregar CadastroProfessoresADM.fxml", e);
            }
        } else {
            log.error("PainelPrincipalController não foi injetado em MenuADMController.");
        }
    }

    @FXML
    void abrirCadastrarProfTGUni(ActionEvent event) {
        // Implemente similarmente para a outra tela quando necessário
        log.info("Abrir cadastro de professores universitários - implementar");
    }

}