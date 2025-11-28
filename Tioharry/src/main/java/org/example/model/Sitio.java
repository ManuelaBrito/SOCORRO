package org.example.model;

public class Sitio {
    private int id;
    private String tamanho;
    private String duende;
    private String cogumelo;

    // Construtor vazio
    public Sitio() {}

    // Construtor sem ID
    public Sitio(String tamanho, String duende, String cogumelo) {
        this.tamanho = tamanho;
        this.duende = duende;
        this.cogumelo = cogumelo;
    }

    // Construtor com ID
    public Sitio(int id, String tamanho, String duende, String cogumelo) {
        this.id = id;
        this.tamanho = tamanho;
        this.duende = duende;
        this.cogumelo = cogumelo;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }
    public String getDuende() { return duende; }
    public void setDuende(String duende) { this.duende = duende; }
    public String getCogumelo() { return cogumelo; }
    public void setCogumelo(String cogumelo) { this.cogumelo = cogumelo; }

    // Método para atualizar todos os dados (exceto ID)
    public void atualizarDados(String tamanho, String duende, String cogumelo) {
        this.tamanho = tamanho;
        this.duende = duende;
        this.cogumelo = cogumelo;
    }

    // Método para atualizar dados a partir de outro objeto Sitio
    public void atualizarDe(Sitio outroSitio) {
        if (outroSitio != null) {
            this.tamanho = outroSitio.getTamanho();
            this.duende = outroSitio.getDuende();
            this.cogumelo = outroSitio.getCogumelo();
        }
    }

    // Método para verificar se os dados são válidos para edição
    public boolean dadosValidos() {
        return tamanho != null && !tamanho.trim().isEmpty() &&
                duende != null && !duende.trim().isEmpty() &&
                cogumelo != null && !cogumelo.trim().isEmpty();
    }

    // Método para criar uma cópia do objeto (útil para edição)
    public Sitio copiar() {
        return new Sitio(this.id, this.tamanho, this.duende, this.cogumelo);
    }

    // Método para verificar se é igual a outro Sitio (baseado no ID)
    public boolean equals(Sitio outro) {
        if (this == outro) return true;
        if (outro == null) return false;
        return this.id == outro.id;
    }

    // Método para resetar os dados (mantém apenas o ID)
    public void resetar() {
        this.tamanho = "";
        this.duende = "";
        this.cogumelo = "";
    }

    // Método para verificar se está vazio (sem dados básicos)
    public boolean isEmpty() {
        return (tamanho == null || tamanho.trim().isEmpty()) &&
                (duende == null || duende.trim().isEmpty()) &&
                (cogumelo == null || cogumelo.trim().isEmpty());
    }

    // Método para validar tamanho específico (opcional)
    public boolean tamanhoValido() {
        if (tamanho == null) return false;
        String tamanhoLower = tamanho.toLowerCase().trim();
        return tamanhoLower.equals("pequeno") ||
                tamanhoLower.equals("médio") ||
                tamanhoLower.equals("grande") ||
                tamanhoLower.equals("pequena") ||
                tamanhoLower.equals("média") ||
                tamanhoLower.equals("grande");
    }

    // Método para validar tipo de duende (opcional)
    public boolean duendeValido() {
        if (duende == null) return false;
        String duendeLower = duende.toLowerCase().trim();
        return duendeLower.equals("sim") ||
                duendeLower.equals("não") ||
                duendeLower.equals("nao") ||
                duendeLower.equals("yes") ||
                duendeLower.equals("no");
    }

    // Método para obter informações resumidas
    public String getInfoResumida() {
        return String.format("Sítio %s - Duende: %s - Cogumelo: %s", tamanho, duende, cogumelo);
    }

    // Método para obter informações detalhadas
    public String getInfoDetalhada() {
        return String.format("ID: %d | Tamanho: %s | Duende: %s | Cogumelo: %s",
                id, tamanho, duende, cogumelo);
    }

    // Método para clonar o objeto (cópia profunda)
    public Sitio clonar() {
        return new Sitio(this.id, this.tamanho, this.duende, this.cogumelo);
    }

    // Método para comparar conteúdo (ignorando ID)
    public boolean mesmoConteudo(Sitio outro) {
        if (outro == null) return false;
        return (this.tamanho == null ? outro.tamanho == null : this.tamanho.equals(outro.tamanho)) &&
                (this.duende == null ? outro.duende == null : this.duende.equals(outro.duende)) &&
                (this.cogumelo == null ? outro.cogumelo == null : this.cogumelo.equals(outro.cogumelo));
    }

    // Método para verificar se é um sítio mágico (tem duende e cogumelo)
    public boolean isMagico() {
        return "sim".equalsIgnoreCase(duende) && cogumelo != null && !cogumelo.trim().isEmpty();
    }

    // Método para classificar o sítio por tamanho
    public String classificarTamanho() {
        if (tamanho == null) return "Desconhecido";

        String tamanhoLower = tamanho.toLowerCase().trim();
        switch (tamanhoLower) {
            case "pequeno": case "pequena":
                return "Pequeno";
            case "médio": case "media": case "média":
                return "Médio";
            case "grande":
                return "Grande";
            default:
                return "Tamanho não classificado";
        }
    }

    @Override
    public String toString() {
        return "Sítio " + tamanho + " - Duende: " + duende + " - Cogumelo: " + cogumelo;
    }

    // Método para exibir dados formatados (útil para logs)
    public String toDetailedString() {
        return String.format("Sitio[ID=%d, Tamanho='%s', Duende='%s', Cogumelo='%s']",
                id, tamanho, duende, cogumelo);
    }

    public void morar() {
        System.out.println("Alguém está morando no sítio " + tamanho + "!");
    }

    // Método adicional: descrever o sítio
    public String descrever() {
        String descricao = "Este é um sítio " + tamanho;

        if ("sim".equalsIgnoreCase(duende)) {
            descricao += " que possui um duende mágico";
        } else {
            descricao += " sem duendes";
        }

        if (cogumelo != null && !cogumelo.trim().isEmpty()) {
            descricao += " e com cogumelos " + cogumelo;
        }

        return descricao + ".";
    }
}