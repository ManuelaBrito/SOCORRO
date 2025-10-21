package org.example;

public class Sitio {
    private String tamanho;
    private String duende;
    private String cogumelo;

    public Sitio(String tamanho, String duende, String cogumelo) {
        this.tamanho = tamanho;
        this.duende = duende;
        this.cogumelo = cogumelo;
    }

    public String getTamanho() { return tamanho; }
    public String getDuende() { return duende; }
    public String getCogumelo() { return cogumelo; }

    // CORRIGIDO: deve retornar String, não void
    public String morar() {
        return "O duende " + this.duende + " está morando no sítio!";
    }
}