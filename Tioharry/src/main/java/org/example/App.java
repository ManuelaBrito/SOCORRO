package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
        primaryStage.setTitle("Sítio do Tio Harry");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    // Adicionar este método
    public static void setRoot(String fxml) throws Exception {
        Parent root = FXMLLoader.load(App.class.getResource(fxml));
        primaryStage.getScene().setRoot(root);
    }

    public static void main(String[] args) {
        launch(args);
    }
}