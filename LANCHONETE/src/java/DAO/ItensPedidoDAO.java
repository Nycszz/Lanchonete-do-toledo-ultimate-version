package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ItensPedido;
import model.Pedido;
import model.Produto;
import util.Conexao;

public class ItensPedidoDAO {

    public boolean inserir(ItensPedido item) {
        String sql = "INSERT INTO itens_pedido "
                   + "(pedidoId, produtoId, quantidade, subtotal) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection con = Conexao.getConexaoMySQL();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, item.getPedido().getId());
            stmt.setInt(2, item.getProduto().getId());
            stmt.setInt(3, item.getQuantidade());
            stmt.setDouble(4, item.getSubtotal());

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao inserir item do pedido: " + e.getMessage());
            return false;
        }
    }

    public List<ItensPedido> listarPorPedido(int pedidoId) {
        List<ItensPedido> lista = new ArrayList<>();

        String sql = "SELECT * FROM itens_pedido WHERE pedidoId = ?";

        try (Connection con = Conexao.getConexaoMySQL();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, pedidoId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ItensPedido item = new ItensPedido();

                    item.setId(rs.getInt("id"));
                    item.setQuantidade(rs.getInt("quantidade"));
                    item.setSubtotal(rs.getDouble("subtotal"));

                    Pedido pedido = new Pedido();
                    pedido.setId(rs.getInt("pedidoId"));
                    item.setPedido(pedido);

                    Produto produto = new Produto();
                    produto.setId(rs.getInt("produtoId"));
                    item.setProduto(produto);

                    lista.add(item);
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar itens do pedido: " + e.getMessage());
        }

        return lista;
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM itens_pedido WHERE id = ?";

        try (Connection con = Conexao.getConexaoMySQL();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao excluir item do pedido: " + e.getMessage());
            return false;
        }
    }
}