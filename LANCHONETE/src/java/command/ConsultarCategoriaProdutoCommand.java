package command;

import DAO.ProdutoDAO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConsultarCategoriaProdutoCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            String categoria = req.getParameter("categoria");

            ProdutoDAO dao = new ProdutoDAO();

            req.setAttribute("produtos", dao.consultarPorCategoria(categoria));
            req.setAttribute("categoriaSelecionada", categoria);

            return "resultadoconsultartodos.jsp";

        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
            return "erro.jsp";
        }
    }
}