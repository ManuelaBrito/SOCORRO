package org.example.DAO;

import org.example.DatabaseConnection;
import org.example.model.Planta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlantaDAO {

    // MÉTODO ADICIONADO: Criar tabela se não existir
    public void criarTabelaSeNaoExistir() {
        String sql = "CREATE TABLE IF NOT EXISTS plantas (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "tamanho VARCHAR(50) NOT NULL, " +
                "cor VARCHAR(50) NOT NULL, " +
                "especie VARCHAR(50) NOT NULL)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.execute();
            System.out.println("Tabela 'plantas' verificada/criada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela plantas: " + e.getMessage());
        }
    }

    // MÉTODO ROBUSTO: Forçar criação da tabela com verificação
    public void criarTabelaPlantasForcadamente() {
        System.out.println("=== TENTANDO CRIAR TABELA PLANTAS ===");

        // Primeiro verifica se a tabela já existe
        if (tabelaExiste()) {
            System.out.println("✅ Tabela 'plantas' já existe!");
            return;
        }

        // Lista de comandos SQL alternativos
        String[] sqlCommands = {
                // Comando 1: CREATE TABLE IF NOT EXISTS
                "CREATE TABLE IF NOT EXISTS plantas (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "tamanho VARCHAR(50) NOT NULL, " +
                        "cor VARCHAR(50) NOT NULL, " +
                        "especie VARCHAR(50) NOT NULL)",

                // Comando 2: CREATE TABLE simples (sem IF NOT EXISTS)
                "CREATE TABLE plantas (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "tamanho VARCHAR(50) NOT NULL, " +
                        "cor VARCHAR(50) NOT NULL, " +
                        "especie VARCHAR(50) NOT NULL)",

                // Comando 3: Com sintaxe alternativa
                "CREATE TABLE IF NOT EXISTS `plantas` (" +
                        "`id` INT AUTO_INCREMENT PRIMARY KEY, " +
                        "`tamanho` VARCHAR(50) NOT NULL, " +
                        "`cor` VARCHAR(50) NOT NULL, " +
                        "`especie` VARCHAR(50) NOT NULL)"
        };

        for (int i = 0; i < sqlCommands.length; i++) {
            String sql = sqlCommands[i];
            System.out.println("Tentativa " + (i + 1) + ": " + sql.substring(0, Math.min(50, sql.length())) + "...");

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.execute();
                System.out.println("✅ Comando executado com sucesso!");

                // Verifica se a tabela foi criada
                if (tabelaExiste()) {
                    System.out.println("✅ Tabela 'plantas' criada com sucesso na tentativa " + (i + 1));
                    return;
                } else {
                    System.out.println("❌ Comando executou mas tabela não foi criada");
                }

            } catch (SQLException e) {
                System.out.println("❌ Erro na tentativa " + (i + 1) + ": " + e.getMessage());
            }
        }

        // Se chegou aqui, nenhum comando funcionou
        System.out.println("❌ TODAS AS TENTATIVAS DE CRIAR A TABELA PLANTAS FALHARAM!");
        verificarProblemasConexao();
    }

    // Método para verificar problemas de conexão/permissões
    private void verificarProblemasConexao() {
        System.out.println("=== VERIFICANDO CONEXÃO E PERMISSÕES ===");

        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("✅ Conexão com banco estabelecida");

            // Verificar permissões tentando criar uma tabela temporária
            String testTableSQL = "CREATE TABLE IF NOT EXISTS test_permission (" +
                    "id INT)";
            try (PreparedStatement stmt = conn.prepareStatement(testTableSQL)) {
                stmt.execute();
                System.out.println("✅ Permissões de criação de tabela OK");

                // Limpar tabela de teste
                try (PreparedStatement dropStmt = conn.prepareStatement("DROP TABLE IF EXISTS test_permission")) {
                    dropStmt.execute();
                    System.out.println("✅ Permissões de exclusão de tabela OK");
                }
            }

            // Verificar se o database existe
            String checkDBSQL = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'tioharry'";
            try (PreparedStatement stmt = conn.prepareStatement(checkDBSQL);
                 ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    System.out.println("✅ Database 'tioharry' existe");
                } else {
                    System.out.println("❌ Database 'tioharry' NÃO existe");
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Problema de conexão/permissões: " + e.getMessage());
        }
    }

    // Método melhorado para verificar se a tabela existe
    public boolean tabelaExiste() {
        String[] checkQueries = {
                "SELECT 1 FROM plantas LIMIT 1",
                "SHOW TABLES LIKE 'plantas'",
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'tioharry' AND TABLE_NAME = 'plantas'"
        };

        for (String sql : checkQueries) {
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    System.out.println("✅ Tabela 'plantas' existe (verificado com: " + sql.substring(0, 30) + "...)");
                    return true;
                }

            } catch (SQLException e) {
                // Continua para a próxima query
            }
        }

        System.out.println("❌ Tabela 'plantas' NÃO existe");
        return false;
    }

    public void inserirPlanta(Planta planta) {
        String sql = "INSERT INTO plantas (tamanho, cor, especie) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, planta.getTamanho());
            stmt.setString(2, planta.getCor());
            stmt.setString(3, planta.getEspecie());
            stmt.executeUpdate();

            // Recuperar o ID gerado
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                planta.setId(generatedKeys.getInt(1));
            }

            System.out.println("Planta inserida com sucesso! ID: " + planta.getId());

        } catch (SQLException e) {
            System.out.println("Erro ao inserir planta: " + e.getMessage());
        }
    }

    public List<Planta> listarPlantas() {
        String sql = "SELECT * FROM plantas";
        List<Planta> plantas = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Planta planta = new Planta(
                        rs.getInt("id"),
                        rs.getString("tamanho"),
                        rs.getString("cor"),
                        rs.getString("especie")
                );
                plantas.add(planta);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar plantas: " + e.getMessage());
        }
        return plantas;
    }

    public Planta buscarPorId(int id) {
        String sql = "SELECT * FROM plantas WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Planta(
                        rs.getInt("id"),
                        rs.getString("tamanho"),
                        rs.getString("cor"),
                        rs.getString("especie")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar planta por ID: " + e.getMessage());
        }
        return null;
    }

    // CORREÇÃO: Método atualizado para receber apenas o objeto Planta
    public void atualizarPlanta(Planta planta) {
        String sql = "UPDATE plantas SET tamanho = ?, cor = ?, especie = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, planta.getTamanho());
            stmt.setString(2, planta.getCor());
            stmt.setString(3, planta.getEspecie());
            stmt.setInt(4, planta.getId());
            stmt.executeUpdate();

            System.out.println("Planta atualizada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar planta: " + e.getMessage());
        }
    }

    // Método para excluir por ID
    public void excluirPlanta(int id) {
        String sql = "DELETE FROM plantas WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Planta ID " + id + " excluída com sucesso!");
            } else {
                System.out.println("Planta não encontrada!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir planta: " + e.getMessage());
        }
    }

    // Método original mantido para compatibilidade
    public void excluirPlanta(String especie) {
        String sql = "DELETE FROM plantas WHERE especie = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, especie);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Planta " + especie + " excluída com sucesso!");
            } else {
                System.out.println("Planta não encontrada!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir planta: " + e.getMessage());
        }
    }

    // Métodos adicionais úteis
    public List<Planta> buscarPorEspecie(String especie) {
        String sql = "SELECT * FROM plantas WHERE especie = ?";
        List<Planta> plantas = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, especie);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Planta planta = new Planta(
                        rs.getInt("id"),
                        rs.getString("tamanho"),
                        rs.getString("cor"),
                        rs.getString("especie")
                );
                plantas.add(planta);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar plantas por espécie: " + e.getMessage());
        }
        return plantas;
    }

    public int contarPlantas() {
        String sql = "SELECT COUNT(*) as total FROM plantas";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao contar plantas: " + e.getMessage());
        }
        return 0;
    }
}