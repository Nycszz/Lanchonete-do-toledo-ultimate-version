package command;

import DAO.ProdutoDAO;
import model.Produto;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ExcluirProdutoCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("txtid"));

            Produto p = new Produto();
            p.setId(id);

            ProdutoDAO dao = new ProdutoDAO();
            dao.deletar(p);

            res.sendRedirect("controle_produto?op=ConsultarTodosProduto");
            return null;

        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
            return "erro.jsp";
        }
    }
}