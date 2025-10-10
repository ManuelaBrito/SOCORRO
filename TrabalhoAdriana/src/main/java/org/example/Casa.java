package org.example;

import java.io.File;

public class Casa {

    private String localizacao;
    private String cor;
    private String tamanho;

    // Construtor vazio
    public Casa() {
    }

    // Construtor completo
    public Casa(String localizacao, String cor, String tamanho) {
        this.localizacao = localizacao;
        this.cor = cor;
        this.tamanho = tamanho;
    }

    // Método principal (pode ser usado para lógica específica)
    public void acaoPrincipal() {
        System.out.println("A casa está servindo para morar, armazenar e proteger!");
    }

    // Getters e Setters
    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
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

        // Cria o arquivo "casa.csv" dentro da pasta
        File arquivo = new File(pasta, "casa.csv");

        // Cabeçalho — só é criado se o arquivo ainda não existir
        String[] cabecalho = arquivo.exists() ? null : new String[]{"LOCALIZACAO", "COR", "TAMANHO"};

        // Dados da casa a serem gravados
        String[][] dados = {
                {getLocalizacao(), getCor(), getTamanho()}
        };

        // Utiliza a classe CsvEscritaLeitura para salvar
        CsvEscritaLeitura csv = new CsvEscritaLeitura();
        csv.escreverCSV(arquivo.getPath(), cabecalho, dados);
    }
}
