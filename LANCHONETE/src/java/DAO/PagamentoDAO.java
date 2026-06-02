package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Pagamento;
import util.Conexao;
import java.sql.ResultSet;

public class PagamentoDAO {

    public void cadastrar(Pagamento p) throws Exception {
        Connection con = Conexao.getConexaoMySQL();

        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO pagamento (pedidoId, formaPagamento, valor, statusPagamento) VALUES (?, ?, ?, ?)"
        );
        
        ps.setInt(1, p.getPedidoId());
        ps.setString(2, p.getFormaPagamento());
        ps.setDouble(3, p.getValor());
        ps.setString(4, p.getStatusPagamento());

        ps.executeUpdate();
        con.close();
    }
    
    public Pagamento consultarPorPedidoId(int pedidoId) throws Exception {
    Connection con = Conexao.getConexaoMySQL();

    PreparedStatement ps = con.prepareStatement(
        "SELECT * FROM pagamento WHERE pedidoId = ?"
    );

    ps.setInt(1, pedidoId);

    ResultSet rs = ps.executeQuery();

    Pagamento pagamento = null;

    if (rs.next()) {
        pagamento = new Pagamento();
        pagamento.setId(rs.getInt("id"));
        pagamento.setPedidoId(rs.getInt("pedidoId"));
        pagamento.setFormaPagamento(rs.getString("formaPagamento"));
        pagamento.setValor(rs.getDouble("valor"));
        pagamento.setStatusPagamento(rs.getString("statusPagamento"));
    }

    con.close();
    return pagamento;
}

public void atualizarStatusPagamento(int pedidoId, String status) throws Exception {
    Connection con = Conexao.getConexaoMySQL();

    PreparedStatement ps = con.prepareStatement(
        "UPDATE pagamento SET statusPagamento = ? WHERE pedidoId = ?"
    );

    ps.setString(1, status);
    ps.setInt(2, pedidoId);

    ps.executeUpdate();
    con.close();
}
}