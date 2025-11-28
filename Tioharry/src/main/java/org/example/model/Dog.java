package org.example.model;

public class Dog {
    private int id;
    private String nome;
    private String cor;
    private String idioma;

    // Construtor vazio
    public Dog() {}

    // Construtor sem ID
    public Dog(String nome, String cor, String idioma) {
        this.nome = nome;
        this.cor = cor;
        this.idioma = idioma;
    }

    // Construtor com ID
    public Dog(int id, String nome, String cor, String idioma) {
        this.id = id;
        this.nome = nome;
        this.cor = cor;
        this.idioma = idioma;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    // Método para atualizar todos os dados (exceto ID)
    public void atualizarDados(String nome, String cor, String idioma) {
        this.nome = nome;
        this.cor = cor;
        this.idioma = idioma;
    }

    // Método para atualizar dados a partir de outro objeto Dog
    public void atualizarDe(Dog outroDog) {
        if (outroDog != null) {
            this.nome = outroDog.getNome();
            this.cor = outroDog.getCor();
            this.idioma = outroDog.getIdioma();
        }
    }

    // Método para verificar se os dados são válidos para edição
    public boolean dadosValidos() {
        return nome != null && !nome.trim().isEmpty() &&
                cor != null && !cor.trim().isEmpty() &&
                idioma != null && !idioma.trim().isEmpty();
    }

    // Método para criar uma cópia do objeto (útil para edição)
    public Dog copiar() {
        return new Dog(this.id, this.nome, this.cor, this.idioma);
    }

    // Método para verificar se é igual a outro Dog (baseado no ID)
    public boolean equals(Dog outro) {
        if (this == outro) return true;
        if (outro == null) return false;
        return this.id == outro.id;
    }

    // Método para resetar os dados (mantém apenas o ID)
    public void resetar() {
        this.nome = "";
        this.cor = "";
        this.idioma = "";
    }

    public String acompanhar() {
        return "O cachorro " + this.nome + " está acompanhando todos!";
    }

    @Override
    public String toString() {
        return "ID: " + id + " - " + nome + " - " + cor + " (" + idioma + ")";
    }

    // Método para exibir dados formatados (útil para logs)
    public String toDetailedString() {
        return String.format("Dog[ID=%d, Nome='%s', Cor='%s', Idioma='%s']",
                id, nome, cor, idioma);
    }

    // Método para verificar se está vazio (sem dados básicos)
    public boolean isEmpty() {
        return (nome == null || nome.trim().isEmpty()) &&
                (cor == null || cor.trim().isEmpty()) &&
                (idioma == null || idioma.trim().isEmpty());
    }
}