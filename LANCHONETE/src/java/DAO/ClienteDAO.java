package DAO;
import java.sql.*;
import java.util.*;
import model.Cliente;
import util.Conexao;

public class ClienteDAO {

    public void cadastrar(Cliente c) throws Exception {
        Connection con = Conexao.getConexaoMySQL();

        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO clientes (nome, email, telefone) VALUES (?,?,?)"
        );

        ps.setString(1, c.getNome());
        ps.setString(2, c.getEmail());
        ps.setString(3, c.getTelefone());

        ps.execute();
        con.close();
    }

    public List<Cliente> consultarTodos() throws Exception {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM clientes");
        ResultSet rs = ps.executeQuery();

        List<Cliente> lista = new ArrayList<>();

        while (rs.next()) {
            Cliente c = new Cliente();
            c.setId(rs.getInt("id"));
            c.setNome(rs.getString("nome"));
            c.setEmail(rs.getString("email"));
            c.setTelefone(rs.getString("telefone"));
            lista.add(c);
        }

        con.close();
        return lista;
    }

    public void deletar(int id) throws Exception {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement ps = con.prepareStatement("DELETE FROM clientes WHERE id=?");
        ps.setInt(1, id);
        ps.execute();
        con.close();
    }
}
