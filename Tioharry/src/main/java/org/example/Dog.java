package org.example;

public class Dog {
    private String nome;
    private String cor;
    private String idioma;

    public Dog(String nome, String cor, String idioma) {
        this.nome = nome;
        this.cor = cor;
        this.idioma = idioma;
    }

    public String getNome() { return nome; }
    public String getCor() { return cor; }
    public String getIdioma() { return idioma; }

    // CORRIGIDO: deve retornar String, não void
    public String acompanhar() {
        return "O cachorro " + this.nome + " está acompanhando todos!";
    }
}