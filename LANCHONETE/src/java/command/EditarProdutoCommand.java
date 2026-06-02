package command;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class EditarProdutoCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setAttribute("abaAtiva", "produtos");
        return "index.jsp";
    }
}