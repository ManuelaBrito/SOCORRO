package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/Tioharry?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Matuzalem.09";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do MySQL não encontrado!", e);
        }
    }

    public static void testarConexao() {
        try (Connection conn = getConnection()) {
            System.out.println("✅ Conectado ao banco Tioharry com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro na conexão: " + e.getMessage());
        }
    }
}