package command;

import DAO.PedidoDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AtualizarStatusPedidoCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String status = req.getParameter("status");

            PedidoDAO dao = new PedidoDAO();
            dao.atualizarStatus(id, status);

            res.sendRedirect("controle_pedido?op=ConsultarTodos");
            return null;

        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
            return "erro.jsp";
        }
    }
}