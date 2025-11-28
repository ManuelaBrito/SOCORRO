package org.example.DAO;

import org.example.DatabaseConnection;
import org.example.model.Dog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DogDAO {

    // MÉTODO ADICIONADO: Criar tabela se não existir
    public void criarTabelaSeNaoExistir() {
        String sql = "CREATE TABLE IF NOT EXISTS dogs (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nome VARCHAR(50) NOT NULL, " +
                "cor VARCHAR(50) NOT NULL, " +
                "idioma VARCHAR(50) NOT NULL)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.execute();
            System.out.println("Tabela 'dogs' verificada/criada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela dogs: " + e.getMessage());
        }
    }

    public void inserirDog(Dog dog) {
        String sql = "INSERT INTO dogs (nome, cor, idioma) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, dog.getNome());
            stmt.setString(2, dog.getCor());
            stmt.setString(3, dog.getIdioma());
            stmt.executeUpdate();

            // Recuperar o ID gerado
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                dog.setId(generatedKeys.getInt(1));
            }

            System.out.println("Cachorro " + dog.getNome() + " inserido com sucesso! ID: " + dog.getId());

        } catch (SQLException e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
        }
    }

    public List<Dog> listarDogs() {
        String sql = "SELECT * FROM dogs";
        List<Dog> dogs = new ArrayList<>(); // SEMPRE inicializa a lista

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Dog dog = new Dog(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cor"),
                        rs.getString("idioma")
                );
                dogs.add(dog);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
        return dogs; // SEMPRE retorna uma lista (pode estar vazia)
    }

    public Dog buscarPorId(int id) {
        String sql = "SELECT * FROM dogs WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Dog(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cor"),
                        rs.getString("idioma")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar por ID: " + e.getMessage());
        }
        return null;
    }

    public Dog buscarPorNome(String nome) {
        String sql = "SELECT * FROM dogs WHERE nome = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Dog(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cor"),
                        rs.getString("idioma")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        }
        return null;
    }

    public void atualizarDog(Dog dog) {
        String sql = "UPDATE dogs SET nome = ?, cor = ?, idioma = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dog.getNome());
            stmt.setString(2, dog.getCor());
            stmt.setString(3, dog.getIdioma());
            stmt.setInt(4, dog.getId());
            stmt.executeUpdate();

            System.out.println("Cachorro atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    public void excluirDog(int id) {
        String sql = "DELETE FROM dogs WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Cachorro ID " + id + " excluído com sucesso!");
            } else {
                System.out.println("Cachorro não encontrado!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }
    }

    // Método original mantido para compatibilidade
    public void excluirDog(String nome) {
        String sql = "DELETE FROM dogs WHERE nome = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Cachorro " + nome + " excluído com sucesso!");
            } else {
                System.out.println("Cachorro não encontrado!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }
    }

    // Métodos adicionais úteis
    public int contarDogs() {
        String sql = "SELECT COUNT(*) as total FROM dogs";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao contar cachorros: " + e.getMessage());
        }
        return 0;
    }

    public List<Dog> buscarPorCor(String cor) {
        String sql = "SELECT * FROM dogs WHERE cor = ?";
        List<Dog> dogs = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cor);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Dog dog = new Dog(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cor"),
                        rs.getString("idioma")
                );
                dogs.add(dog);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar por cor: " + e.getMessage());
        }
        return dogs;
    }

    public List<Dog> buscarPorIdioma(String idioma) {
        String sql = "SELECT * FROM dogs WHERE idioma = ?";
        List<Dog> dogs = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idioma);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Dog dog = new Dog(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cor"),
                        rs.getString("idioma")
                );
                dogs.add(dog);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar por idioma: " + e.getMessage());
        }
        return dogs;
    }

    public boolean existeDog(String nome) {
        String sql = "SELECT COUNT(*) as total FROM dogs WHERE nome = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("total") > 0;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao verificar cachorro: " + e.getMessage());
        }
        return false;
    }

    // MÉTODO ADICIONADO: Verificar se a tabela existe
    public boolean tabelaExiste() {
        String sql = "SELECT 1 FROM dogs LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            return true; // Se chegou aqui, a tabela existe
        } catch (SQLException e) {
            System.out.println("Tabela 'dogs' não existe ou há erro de conexão: " + e.getMessage());
            return false;
        }
    }
}