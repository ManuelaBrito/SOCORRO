package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Stage stage;
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;

        // Carrega o FXML
        Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));

        // Cria a cena
        scene = new Scene(root, 600, 400);

        // Configura o stage
        stage.setScene(scene);
        stage.setTitle("Sistema de Cadastro - Sítio, Plantas e Cachorros");
        stage.setMinWidth(600);
        stage.setMinHeight(450);

        // Exibe a janela
        stage.show();
    }

    /**
     * Método para trocar a tela (root) da aplicação
     * @param fxml Nome do arquivo FXML (sem extensão)
     * @throws IOException Se o arquivo FXML não for encontrado
     */
    public static void setRoot(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        scene.setRoot(root);
    }

    /**
     * Método para carregar um arquivo FXML
     * @param fxml Nome do arquivo FXML (sem extensão)
     * @return Parent com a hierarquia de nodes carregada
     * @throws IOException Se o arquivo FXML não for encontrado
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Retorna o stage principal da aplicação
     * @return Stage principal
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * Retorna a cena principal da aplicação
     * @return Scene principal
     */
    public static Scene getScene() {
        return scene;
    }

    /**
     * Define o título da janela
     * @param title Título da janela
     */
    public static void setTitle(String title) {
        if (stage != null) {
            stage.setTitle(title);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}