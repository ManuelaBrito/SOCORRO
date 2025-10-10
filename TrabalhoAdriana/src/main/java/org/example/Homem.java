package org.example;

import java.io.File;

public class Homem {

    private double altura;
    private String cor;
    private String personalidade;

    // Construtor vazio
    public Homem() {
    }

    // Construtor completo
    public Homem(double altura, String cor, String personalidade) {
        this.altura = altura;
        this.cor = cor;
        this.personalidade = personalidade;
    }

    // Método principal (pode ser usado para lógica específica)
    public void acaoPrincipal() {
        System.out.println("O homem está entretendo, recebendo e sofrendo!");
    }

    // Getters e Setters
    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPersonalidade() {
        return personalidade;
    }

    public void setPersonalidade(String personalidade) {
        this.personalidade = personalidade;
    }

    // -----------------------------
    // MÉTODO PARA SALVAR EM CSV
    // -----------------------------
    public void salvar() {
        // Cria a pasta "arquivosCSV" se não existir
        File pasta = new File("arquivosCSV");
        if (!pasta.exists()) {
            pasta.mkdir();
        }

        // Cria o arquivo "homem.csv" dentro da pasta
        File arquivo = new File(pasta, "homem.csv");

        // Cabeçalho — só é criado se o arquivo ainda não existir
        String[] cabecalho = arquivo.exists() ? null : new String[]{"ALTURA", "COR", "PERSONALIDADE"};

        // Dados do homem a serem gravados
        String[][] dados = {
                {String.valueOf(getAltura()), getCor(), getPersonalidade()}
        };

        // Utiliza a classe CsvEscritaLeitura para salvar
        CsvEscritaLeitura csv = new CsvEscritaLeitura();
        csv.escreverCSV(arquivo.getPath(), cabecalho, dados);
    }
}
