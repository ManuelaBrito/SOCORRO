package org.example;

public class Planta {
    private String tamanho;
    private String cor;
    private String especie;

    public Planta(String tamanho, String cor, String especie) {
        this.tamanho = tamanho;
        this.cor = cor;
        this.especie = especie;
    }

    public String getTamanho() { return tamanho; }
    public String getCor() { return cor; }
    public String getEspecie() { return especie; }

    // CORRIGIDO: deve retornar String, não void
    public String arborizar() {
        return "A planta " + this.especie + " está arborizando o ambiente!";
    }
}