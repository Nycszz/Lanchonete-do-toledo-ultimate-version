/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;

/**
 *
 * @author Adriana
 */
import DAO.ClienteDAO;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;

public class ConsultarTodosClienteCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            ClienteDAO dao = new ClienteDAO();
            req.setAttribute("clientes", dao.consultarTodos());

            return "clientes.jsp";

        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
            return "erro.jsp";
        }
    }
}