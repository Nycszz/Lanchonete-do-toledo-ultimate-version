package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Pedido;
import util.Conexao;

public class PedidoDAO {

    public int cadastrarPedido(Pedido p) throws Exception {
        Connection con = Conexao.getConexaoMySQL();

        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO pedidos (clienteId, valorTotal, observacao, status) VALUES (?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS
        );

        ps.setInt(1, p.getClienteId());
        ps.setDouble(2, p.getValorTotal());
        ps.setString(3, p.getObservacao());
        ps.setString(4, p.getStatus());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        int pedidoId = 0;

        if (rs.next()) {
            pedidoId = rs.getInt(1);
        }

        con.close();
        return pedidoId;
    }

    public void cadastrarItemPedido(int pedidoId, int produtoId, int quantidade, double subtotal) throws Exception {
        Connection con = Conexao.getConexaoMySQL();

        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO itens_pedido (pedidoId, produtoId, quantidade, subtotal) VALUES (?, ?, ?, ?)"
        );

        ps.setInt(1, pedidoId);
        ps.setInt(2, produtoId);
        ps.setInt(3, quantidade);
        ps.setDouble(4, subtotal);

        ps.executeUpdate();
        con.close();
    }

    public List<Pedido> consultarTodos() throws Exception {
        Connection con = Conexao.getConexaoMySQL();

       String sql =
    "SELECT p.id, c.nome AS clienteNome, p.valorTotal, p.observacao, p.status, " +
    "pg.formaPagamento, pg.statusPagamento " +
    "FROM pedidos p " +
    "JOIN clientes c ON p.clienteId = c.id " +
    "LEFT JOIN pagamento pg ON p.id = pg.pedidoId";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Pedido> lista = new ArrayList<>();

        while (rs.next()) {
            Pedido p = new Pedido();

            p.setId(rs.getInt("id"));
            p.setClienteNome(rs.getString("clienteNome"));
            p.setValorTotal(rs.getDouble("valorTotal"));
            p.setObservacao(rs.getString("observacao"));
            p.setStatus(rs.getString("status"));
            p.setFormaPagamento(rs.getString("formaPagamento"));
            p.setStatusPagamento(rs.getString("statusPagamento"));

            lista.add(p);
        }

        con.close();
        return lista;
    }

    public void atualizarStatus(int pedidoId, String status) throws Exception {
        Connection con = Conexao.getConexaoMySQL();

        PreparedStatement ps = con.prepareStatement(
            "UPDATE pedidos SET status = ? WHERE id = ?"
        );

        ps.setString(1, status);
        ps.setInt(2, pedidoId);

        ps.executeUpdate();
        con.close();
    }

public List<Pedido> consultarPorStatus(String status) throws Exception {
    Connection con = Conexao.getConexaoMySQL();

    PreparedStatement ps;

    if (status == null || status.equals("") || status.equals("Todos")) {
        ps = con.prepareStatement(
            "SELECT p.id, c.nome AS clienteNome, p.valorTotal, p.observacao, p.status " +
            "FROM pedidos p " +
            "JOIN clientes c ON p.clienteId = c.id"
        );
    } else {
        ps = con.prepareStatement(
            "SELECT p.id, c.nome AS clienteNome, p.valorTotal, p.observacao, p.status " +
            "FROM pedidos p " +
            "JOIN clientes c ON p.clienteId = c.id " +
            "WHERE p.status = ?"
        );
        ps.setString(1, status);
    }

    ResultSet rs = ps.executeQuery();

    List<Pedido> lista = new ArrayList<>();

    while (rs.next()) {
        Pedido p = new Pedido();

        p.setId(rs.getInt("id"));
        p.setClienteNome(rs.getString("clienteNome"));
        p.setValorTotal(rs.getDouble("valorTotal"));
        p.setObservacao(rs.getString("observacao"));
        p.setStatus(rs.getString("status"));

        lista.add(p);
    }

    con.close();
    return lista;
}   
}