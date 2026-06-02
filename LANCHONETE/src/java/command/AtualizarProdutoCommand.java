package command;

import DAO.ProdutoDAO;
import model.Produto;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AtualizarProdutoCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            Produto p = new Produto();

            p.setId(Integer.parseInt(req.getParameter("txtid")));
            p.setDescricao(req.getParameter("txtdescricao"));
            p.setPreco(Double.parseDouble(req.getParameter("txtpreco")));
            p.setCategoria(req.getParameter("txtcategoria"));

            ProdutoDAO dao = new ProdutoDAO();
            dao.atualizar(p);

            res.sendRedirect("controle_produto?op=ConsultarTodosProduto");
            return null;

        } catch (Exception e) {
            req.setAttribute("msg", "Erro ao atualizar produto: " + e.getMessage());
            return "erro.jsp";
        }
    }
}