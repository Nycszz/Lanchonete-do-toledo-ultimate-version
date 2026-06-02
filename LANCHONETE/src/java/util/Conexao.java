package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConexaoPostgres() throws ClassNotFoundException, SQLException {
        
        String DRIVER = "org.postgresql.Driver";
        String URL = "jdbc:postgresql://localhost:5432/lanchonete_bd";
        String USER = "postgres";
        String PASSWORD = "postgres";
        Class.forName(DRIVER);
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        return con;
    }

    public static Connection getConexaoMySQL() throws ClassNotFoundException, SQLException {
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/lanchonete_bd";
        String USER = "root";
        String PASSWORD = "admin";
        Class.forName(DRIVER);
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        return con;
    }

}
