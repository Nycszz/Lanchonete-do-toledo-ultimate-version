package command;

import DAO.ClienteDAO;
import DAO.ProdutoDAO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AbrirPedidoCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            ProdutoDAO produtoDAO = new ProdutoDAO();

            req.setAttribute("clientes", clienteDAO.consultarTodos());
            req.setAttribute("produtos", produtoDAO.consultarTodos());
            req.setAttribute("abaAtiva", "pedidos");

            return "index.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", "Erro ao carregar pedidos: " + e.getMessage());
            return "erro.jsp";
        }
    }
}