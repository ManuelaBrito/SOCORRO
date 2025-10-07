package org.example;

public class Casa {

    private String localizacao;
    private String cor;
    private String tamanho;


    public Casa(String localizacao, String cor, String tamanho) {
        this.localizacao = localizacao;
        this.cor = cor;
        this.tamanho = tamanho;
    }


    public void acaoPrincipal() {
        System.out.println("A casa está servindo para morar, armazenar e proteger!");
    }


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
}
