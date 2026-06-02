package command;

import DAO.ClienteDAO;
import model.Cliente;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import util.Conexao;

public class AtualizarClienteCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String nome = req.getParameter("nome");
            String email = req.getParameter("email");
            String telefone = req.getParameter("telefone");

            Connection con = Conexao.getConexaoMySQL();

            PreparedStatement ps = con.prepareStatement(
                "UPDATE clientes SET nome=?, email=?, telefone=? WHERE id=?"
            );

            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setString(3, telefone);
            ps.setInt(4, id);

            ps.executeUpdate();
            con.close();

            res.sendRedirect("controle_cliente?op=ConsultarTodos&msg=Cliente atualizado com sucesso!");
            return null;

        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
            return "erro.jsp";
        }
    }
}