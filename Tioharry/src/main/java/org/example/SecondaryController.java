package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SecondaryController {

    @FXML
    private Label resultadoLabel;

    @FXML
    private void switchToPrimary() throws Exception { // ← Adicionar throws Exception
        App.setRoot("primary.fxml");
    }

    @FXML
    private void demonstrarEcossistema() {
        Sitio sitio1 = new Sitio("Pequeno", "Duende verde", "Cogumelo mágico");
        Planta planta1 = new Planta("Média", "Verde", "Samambaia");
        Dog dog1 = new Dog("Robson", "Caramelo", "Inglês");

        StringBuilder sb = new StringBuilder();
        sb.append("=== SÍTIO ===\n");
        sb.append("Tamanho: ").append(sitio1.getTamanho()).append("\n");
        sb.append("Duende: ").append(sitio1.getDuende()).append("\n");
        sb.append("Cogumelo: ").append(sitio1.getCogumelo()).append("\n");

        sb.append("\n=== PLANTA ===\n");
        sb.append("Tamanho: ").append(planta1.getTamanho()).append("\n");
        sb.append("Cor: ").append(planta1.getCor()).append("\n");
        sb.append("Espécie: ").append(planta1.getEspecie()).append("\n");

        sb.append("\n=== CACHORRO ===\n");
        sb.append("Nome: ").append(dog1.getNome()).append("\n");
        sb.append("Cor: ").append(dog1.getCor()).append("\n");
        sb.append("Idioma: ").append(dog1.getIdioma()).append("\n");

        sb.append("\n=== INTERAÇÕES ===\n");
        sb.append("O duende está morando no sítio!\n");
        sb.append("A planta está arborizando o ambiente!\n");
        sb.append("O cachorro está acompanhando todos!\n");

        resultadoLabel.setText(sb.toString());
    }
}