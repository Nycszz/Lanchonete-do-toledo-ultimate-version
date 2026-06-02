package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;
import util.Conexao;

public class ProdutoDAO {

    public void cadastrar(Produto p) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        
        PreparedStatement comando = con.prepareStatement(
            "INSERT INTO produtos (descricao, preco, categoria) VALUES (?,?,?)"
        );
        
        comando.setString(1, p.getDescricao());
        comando.setDouble(2, p.getPreco());
        comando.setString(3, p.getCategoria());
        
        comando.execute();
        con.close();
    }

    public void deletar(Produto p) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        
        PreparedStatement comando = con.prepareStatement(
            "DELETE FROM produtos WHERE id = ?"
        );
        
        comando.setInt(1, p.getId());
        comando.execute();
        con.close();
    }

    public void atualizar(Produto p) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        
        PreparedStatement comando = con.prepareStatement(
            "UPDATE produtos SET descricao = ?, preco = ?, categoria = ? WHERE id = ?"
        );
        
        comando.setString(1, p.getDescricao());
        comando.setDouble(2, p.getPreco());
        comando.setString(3, p.getCategoria());
        comando.setInt(4, p.getId());
        
        
        
        comando.execute();
        con.close();
    }

    public Produto consultarById(Produto p) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        
        PreparedStatement comando = con.prepareStatement(
            "SELECT * FROM produtos WHERE id = ?"
        );
        
        comando.setInt(1, p.getId());
        ResultSet rs = comando.executeQuery();

        Produto prod = null;

        if (rs.next()) {
            prod = Produto.getBuilder()
                    .comId(rs.getInt("id"))
                    .comDescricao(rs.getString("descricao"))
                    .comPreco(rs.getDouble("preco"))
                    .comCategoria(rs.getString("categoria"))
                    .build();
        }

        con.close();
        return prod;
    }

    public List<Produto> consultarTodos() throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        
        PreparedStatement comando = con.prepareStatement("SELECT * FROM produtos");
        ResultSet rs = comando.executeQuery();

        List<Produto> lista = new ArrayList<>();

        while (rs.next()) {
            Produto prod = Produto.getBuilder()
                    .comId(rs.getInt("id"))
                    .comDescricao(rs.getString("descricao"))
                    .comPreco(rs.getDouble("preco"))
                    .comCategoria(rs.getString("categoria"))
                    .build();

            lista.add(prod);
        }

        con.close();
        return lista;
    }
    
    public List<Produto> consultarPorCategoria(String categoria)
        throws ClassNotFoundException, SQLException {

    Connection con = Conexao.getConexaoMySQL();

    PreparedStatement comando;

    if (categoria == null || categoria.equals("") || categoria.equals("Todos")) {
        comando = con.prepareStatement("SELECT * FROM produtos");
    } else {
        comando = con.prepareStatement(
            "SELECT * FROM produtos WHERE categoria = ?"
        );
        comando.setString(1, categoria);
    }

    ResultSet rs = comando.executeQuery();

    List<Produto> lista = new ArrayList<>();

    while (rs.next()) {
        Produto prod = Produto.getBuilder()
                .comId(rs.getInt("id"))
                .comDescricao(rs.getString("descricao"))
                .comPreco(rs.getDouble("preco"))
                .comCategoria(rs.getString("categoria"))
                .build();

        lista.add(prod);
    }

    con.close();
    return lista;
}
}