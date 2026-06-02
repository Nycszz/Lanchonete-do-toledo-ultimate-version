package command;

import DAO.PagamentoDAO;
import DAO.PedidoDAO;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;

public class AtualizarPagamentoPedidoCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            int pedidoId = Integer.parseInt(req.getParameter("id"));
            String status = req.getParameter("status");

            PagamentoDAO pagamentoDAO = new PagamentoDAO();
            pagamentoDAO.atualizarStatusPagamento(pedidoId, status);

            if ("Pago".equals(status)) {
                PedidoDAO pedidoDAO = new PedidoDAO();
                pedidoDAO.atualizarStatus(pedidoId, "Em preparo");
            }

            res.sendRedirect("controle_pedido?op=ConsultarTodos");
            return null;

        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
            return "erro.jsp";
        }
    }
}