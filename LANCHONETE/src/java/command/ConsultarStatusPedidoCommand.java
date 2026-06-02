package command;

import DAO.PedidoDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConsultarStatusPedidoCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        try {
            String status = req.getParameter("status");

            PedidoDAO dao = new PedidoDAO();
            req.setAttribute("pedidos", dao.consultarPorStatus(status));

            return "pedidos.jsp";

        } catch (Exception e) {
            req.setAttribute("msg", "Erro ao consultar pedidos por status: " + e.getMessage());
            return "erro.jsp";
        }
    }
}