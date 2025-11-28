package org.example.model;

public class Planta {
    private int id;
    private String tamanho;
    private String cor;
    private String especie;

    // Construtor vazio
    public Planta() {}

    // Construtor sem ID
    public Planta(String tamanho, String cor, String especie) {
        this.tamanho = tamanho;
        this.cor = cor;
        this.especie = especie;
    }

    // Construtor com ID
    public Planta(int id, String tamanho, String cor, String especie) {
        this.id = id;
        this.tamanho = tamanho;
        this.cor = cor;
        this.especie = especie;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    // Método para atualizar todos os dados (exceto ID)
    public void atualizarDados(String tamanho, String cor, String especie) {
        this.tamanho = tamanho;
        this.cor = cor;
        this.especie = especie;
    }

    // Método para atualizar dados a partir de outro objeto Planta
    public void atualizarDe(Planta outraPlanta) {
        if (outraPlanta != null) {
            this.tamanho = outraPlanta.getTamanho();
            this.cor = outraPlanta.getCor();
            this.especie = outraPlanta.getEspecie();
        }
    }

    // Método para verificar se os dados são válidos para edição
    public boolean dadosValidos() {
        return tamanho != null && !tamanho.trim().isEmpty() &&
                cor != null && !cor.trim().isEmpty() &&
                especie != null && !especie.trim().isEmpty();
    }

    // Método para criar uma cópia do objeto (útil para edição)
    public Planta copiar() {
        return new Planta(this.id, this.tamanho, this.cor, this.especie);
    }

    // Método para verificar se é igual a outra Planta (baseado no ID)
    public boolean equals(Planta outra) {
        if (this == outra) return true;
        if (outra == null) return false;
        return this.id == outra.id;
    }

    // Método para resetar os dados (mantém apenas o ID)
    public void resetar() {
        this.tamanho = "";
        this.cor = "";
        this.especie = "";
    }

    // Método para verificar se está vazio (sem dados básicos)
    public boolean isEmpty() {
        return (tamanho == null || tamanho.trim().isEmpty()) &&
                (cor == null || cor.trim().isEmpty()) &&
                (especie == null || especie.trim().isEmpty());
    }

    // Método para validar tamanho específico (opcional)
    public boolean tamanhoValido() {
        if (tamanho == null) return false;
        String tamanhoLower = tamanho.toLowerCase().trim();
        return tamanhoLower.equals("pequeno") ||
                tamanhoLower.equals("médio") ||
                tamanhoLower.equals("grande");
    }

    // Método para obter informações resumidas
    public String getInfoResumida() {
        return String.format("%s - %s (%s)", especie, cor, tamanho);
    }

    // Método para obter informações detalhadas
    public String getInfoDetalhada() {
        return String.format("Espécie: %s | Cor: %s | Tamanho: %s | ID: %d",
                especie, cor, tamanho, id);
    }

    public String arborizar() {
        return "A planta " + this.especie + " está arborizando o ambiente!";
    }

    @Override
    public String toString() {
        return especie + " - " + cor + " (" + tamanho + ")";
    }

    // Método para exibir dados formatados (útil para logs)
    public String toDetailedString() {
        return String.format("Planta[ID=%d, Espécie='%s', Cor='%s', Tamanho='%s']",
                id, especie, cor, tamanho);
    }

    // Método para clonar o objeto (cópia profunda)
    public Planta clonar() {
        return new Planta(this.id, this.tamanho, this.cor, this.especie);
    }

    // Método para comparar conteúdo (ignorando ID)
    public boolean mesmoConteudo(Planta outra) {
        if (outra == null) return false;
        return (this.tamanho == null ? outra.tamanho == null : this.tamanho.equals(outra.tamanho)) &&
                (this.cor == null ? outra.cor == null : this.cor.equals(outra.cor)) &&
                (this.especie == null ? outra.especie == null : this.especie.equals(outra.especie));
    }
}