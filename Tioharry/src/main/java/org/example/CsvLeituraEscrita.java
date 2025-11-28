package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class CsvLeituraEscrita {

    /**
     * Escreve dados em um arquivo CSV.
     *
     * @param caminho    Caminho do arquivo CSV
     * @param cabecalho  Array com o cabeçalho (ou null se não quiser escrever)
     * @param dados      Matriz com os dados a serem escritos
     */
    public void escreverCSV(String caminho, String[] cabecalho, String[][] dados) {
        try (FileWriter writer = new FileWriter(caminho, true)) { // true = append
            if (cabecalho != null) {
                writer.append(String.join(";", cabecalho)).append("\n");
            }
            for (String[] linha : dados) {
                writer.append(String.join(";", linha)).append("\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}