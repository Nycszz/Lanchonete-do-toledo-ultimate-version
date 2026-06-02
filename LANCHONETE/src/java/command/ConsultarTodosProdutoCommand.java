package command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.ProdutoDAO;
import java.util.List;
import java.util.ArrayList;
import model.Produto;

public class ConsultarTodosProdutoCommand implements ICommand{
    
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException{
        ProdutoDAO pdao = new ProdutoDAO();
        List<Produto> prods = new ArrayList<Produto>();
        try{
            prods = pdao.consultarTodos();
            req.setAttribute("prods", prods);
        }catch(Exception e){
        
        }
        return "resultadoconsultartodos.jsp";
    }
}
