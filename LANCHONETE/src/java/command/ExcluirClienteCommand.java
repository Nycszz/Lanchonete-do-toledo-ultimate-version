package command;

import DAO.ClienteDAO;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;

public class ExcluirClienteCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));

            ClienteDAO dao = new ClienteDAO();
            dao.deletar(id);

            res.sendRedirect("controle_cliente?op=ConsultarTodos&msg=Cliente excluído com sucesso!");
            return null;

        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
            return "erro.jsp";
        }
    }
}