package org.example;

public class Caixa {
    private String tamanho;
    private String cor;
    private String dono;

    public Caixa(String tamanho, String cor, String dono) {
        this.tamanho = tamanho;
        this.cor = cor;
        this.dono = dono;
    }

    public void acaoPrincipal() {
        System.out.println("A caixa está assustando, presenteando e guardando alguem!");
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }
}
