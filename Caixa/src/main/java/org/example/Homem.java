package org.example;

public class Homem {

    private double altura;
    private String cor;
    private String personalidade;


    public Homem(double altura, String cor, String personalidade) {
        this.altura = altura;
        this.cor = cor;
        this.personalidade = personalidade;
    }


    public void acaoPrincipal() {
        System.out.println("O homem está entretendo, recebendo e sofrendo!");
    }


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
}
