package org.example;

import java.io.File;

public class Caixa {

    private String tamanho;
    private String cor;
    private String dono;

    public Caixa() {
    }

    public Caixa(String tamanho, String cor, String dono) {
        this.tamanho = tamanho;
        this.cor = cor;
        this.dono = dono;
    }

    public void acaoPrincipal() {
        System.out.println("A caixa está assustando, presenteando e guardando alguém!");
    }

    // Getters e Setters
    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public String getDono() { return dono; }
    public void setDono(String dono) { this.dono = dono; }

    // -----------------------------
    // MÉTODO PARA SALVAR EM CSV
    // -----------------------------
    public void salvar() {
        // Cria a pasta "arquivosCSV" se não existir
        File pasta = new File("arquivosCSV");
        if (!pasta.exists()) {
            pasta.mkdir();
        }

        // Cria o arquivo "caixa.csv" dentro da pasta
        File arquivo = new File(pasta, "caixa.csv");

        // Cabeçalho — só é criado se o arquivo ainda não existir
        String[] cabecalho = arquivo.exists() ? null : new String[]{"TAMANHO", "COR", "DONO"};

        // Dados da caixa a serem gravados
        String[][] dados = {
                {getTamanho(), getCor(), getDono()}
        };

        // Utiliza a classe CsvEscritaLeitura para salvar
        CsvEscritaLeitura csv = new CsvEscritaLeitura();
        csv.escreverCSV(arquivo.getPath(), cabecalho, dados);
    }
}
