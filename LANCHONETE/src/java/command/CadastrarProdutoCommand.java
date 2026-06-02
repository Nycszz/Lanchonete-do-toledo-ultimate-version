package command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produto;
import DAO.ProdutoDAO;

public class CadastrarProdutoCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse rep)
            throws ServletException, IOException {

        ProdutoDAO pdao = new ProdutoDAO();

        try {
            String desc = req.getParameter("txtdescricao");
            double preco = Double.parseDouble(req.getParameter("txtpreco"));
            String categoria = req.getParameter("txtcategoria");

            Produto p = new Produto();
            p.setDescricao(desc);
            p.setPreco(preco);

            p.setCategoria(categoria);

            pdao.cadastrar(p);

            rep.sendRedirect("controle_produto?op=ConsultarTodosProduto&msg=Cadastrado com sucesso!");
            return null;

        } catch (NumberFormatException e) {
            req.setAttribute("msg", "Erro: preço inválido!");
            return "erro.jsp";

        } catch (Exception e) {
            req.setAttribute("msg", "Erro ao cadastrar: " + e.getMessage());
            return "erro.jsp";
        }
    }
}