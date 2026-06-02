package command;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;

public class EditarClienteCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setAttribute("abaAtiva", "clientes");

        req.setAttribute("idEditar", req.getParameter("id"));
        req.setAttribute("nomeEditar", req.getParameter("nome"));
        req.setAttribute("emailEditar", req.getParameter("email"));
        req.setAttribute("telefoneEditar", req.getParameter("telefone"));

        return "index.jsp";
    }
}