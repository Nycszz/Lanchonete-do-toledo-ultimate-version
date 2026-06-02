package command;
import DAO.ClienteDAO;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import model.Cliente;

public class CadastrarClienteCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse rep)
            throws ServletException, IOException {

        try {
            String nome = req.getParameter("nome");
            String email = req.getParameter("email");
            String telefone = req.getParameter("telefone");

            Cliente c = new Cliente();
            c.setNome(nome);
            c.setEmail(email);
            c.setTelefone(telefone);

            ClienteDAO dao = new ClienteDAO();
            dao.cadastrar(c);

           rep.sendRedirect("controle_cliente?op=ConsultarTodos&msg=Cliente cadastrado com sucesso!");
            return null;

        } catch (Exception e) {
            req.setAttribute("msg", "Erro: " + e.getMessage());
            return "erro.jsp";
        }
    }
}
