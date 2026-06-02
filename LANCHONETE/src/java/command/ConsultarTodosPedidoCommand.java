/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;

/**
 *
 * @author Adriana
 */
import DAO.PedidoDAO;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;

public class ConsultarTodosPedidoCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            PedidoDAO dao = new PedidoDAO();
            req.setAttribute("pedidos", dao.consultarTodos());

            return "pedidos.jsp";

        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
            return "erro.jsp";
        }
    }
}
