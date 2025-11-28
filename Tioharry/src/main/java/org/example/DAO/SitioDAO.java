package org.example.DAO;

import org.example.DatabaseConnection;
import org.example.model.Sitio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SitioDAO {

    public void inserirSitio(Sitio sitio) {
        String sql = "INSERT INTO sitio (tamanho, duende, cogumelo) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, sitio.getTamanho());
            stmt.setString(2, sitio.getDuende());
            stmt.setString(3, sitio.getCogumelo());
            stmt.executeUpdate();

            // Recuperar o ID gerado
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                sitio.setId(generatedKeys.getInt(1));
            }

            System.out.println("Sítio inserido com sucesso! ID: " + sitio.getId());

        } catch (SQLException e) {
            System.out.println("Erro ao inserir sítio: " + e.getMessage());
        }
    }

    public List<Sitio> listarSitios() {
        String sql = "SELECT * FROM sitio";
        List<Sitio> sitios = new ArrayList<>(); // SEMPRE inicializa a lista

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Sitio sitio = new Sitio(
                        rs.getInt("id"),
                        rs.getString("tamanho"),
                        rs.getString("duende"),
                        rs.getString("cogumelo")
                );
                sitios.add(sitio);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar sítios: " + e.getMessage());
            // Mesmo com erro, retorna lista vazia, não null
        }
        return sitios; // SEMPRE retorna uma lista (pode estar vazia)
    }

    public Sitio buscarPorId(int id) {
        String sql = "SELECT * FROM sitio WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Sitio(
                        rs.getInt("id"),
                        rs.getString("tamanho"),
                        rs.getString("duende"),
                        rs.getString("cogumelo")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar sítio por ID: " + e.getMessage());
        }
        return null;
    }

    public Sitio buscarPorTamanho(String tamanho) {
        String sql = "SELECT * FROM sitio WHERE tamanho = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tamanho);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Sitio(
                        rs.getInt("id"),
                        rs.getString("tamanho"),
                        rs.getString("duende"),
                        rs.getString("cogumelo")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar sítio: " + e.getMessage());
        }
        return null;
    }

    public List<Sitio> buscarPorDuende(String duende) {
        String sql = "SELECT * FROM sitio WHERE duende = ?";
        List<Sitio> sitios = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, duende);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Sitio sitio = new Sitio(
                        rs.getInt("id"),
                        rs.getString("tamanho"),
                        rs.getString("duende"),
                        rs.getString("cogumelo")
                );
                sitios.add(sitio);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar sítios por duende: " + e.getMessage());
        }
        return sitios;
    }

    public List<Sitio> buscarPorCogumelo(String cogumelo) {
        String sql = "SELECT * FROM sitio WHERE cogumelo = ?";
        List<Sitio> sitios = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cogumelo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Sitio sitio = new Sitio(
                        rs.getInt("id"),
                        rs.getString("tamanho"),
                        rs.getString("duende"),
                        rs.getString("cogumelo")
                );
                sitios.add(sitio);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar sítios por cogumelo: " + e.getMessage());
        }
        return sitios;
    }

    public void atualizarSitio(Sitio sitio) {
        String sql = "UPDATE sitio SET tamanho = ?, duende = ?, cogumelo = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sitio.getTamanho());
            stmt.setString(2, sitio.getDuende());
            stmt.setString(3, sitio.getCogumelo());
            stmt.setInt(4, sitio.getId());
            stmt.executeUpdate();

            System.out.println("Sítio atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar sítio: " + e.getMessage());
        }
    }

    public void excluirSitio(int id) {
        String sql = "DELETE FROM sitio WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Sítio ID " + id + " excluído com sucesso!");
            } else {
                System.out.println("Sítio não encontrado!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir sítio: " + e.getMessage());
        }
    }

    // Método original mantido para compatibilidade
    public void excluirSitio(String tamanho) {
        String sql = "DELETE FROM sitio WHERE tamanho = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tamanho);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Sítio " + tamanho + " excluído com sucesso!");
            } else {
                System.out.println("Sítio não encontrado!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir sítio: " + e.getMessage());
        }
    }

    public int contarSitios() {
        String sql = "SELECT COUNT(*) as total FROM sitio";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao contar sítios: " + e.getMessage());
        }
        return 0;
    }

    public boolean existeSitio(String tamanho, String duende, String cogumelo) {
        String sql = "SELECT COUNT(*) as total FROM sitio WHERE tamanho = ? AND duende = ? AND cogumelo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tamanho);
            stmt.setString(2, duende);
            stmt.setString(3, cogumelo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("total") > 0;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao verificar sítio: " + e.getMessage());
        }
        return false;
    }

    public List<String> listarTiposDuende() {
        String sql = "SELECT DISTINCT duende FROM sitio";
        List<String> duendes = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                duendes.add(rs.getString("duende"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar tipos de duende: " + e.getMessage());
        }
        return duendes;
    }

    public List<String> listarTiposCogumelo() {
        String sql = "SELECT DISTINCT cogumelo FROM sitio";
        List<String> cogumelos = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                cogumelos.add(rs.getString("cogumelo"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar tipos de cogumelo: " + e.getMessage());
        }
        return cogumelos;
    }

    // Método para verificar se a tabela existe (útil para debug)
    public boolean tabelaExiste() {
        String sql = "SELECT 1 FROM sitio LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            return true; // Se chegou aqui, a tabela existe
        } catch (SQLException e) {
            System.out.println("Tabela 'sitio' não existe ou há erro de conexão: " + e.getMessage());
            return false;
        }
    }

    // Método para criar a tabela se não existir
    public void criarTabelaSeNaoExistir() {
        String sql = "CREATE TABLE IF NOT EXISTS sitio (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "tamanho VARCHAR(50) NOT NULL, " +
                "duende VARCHAR(50) NOT NULL, " +
                "cogumelo VARCHAR(50) NOT NULL)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.execute();
            System.out.println("Tabela 'sitio' verificada/criada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }
}