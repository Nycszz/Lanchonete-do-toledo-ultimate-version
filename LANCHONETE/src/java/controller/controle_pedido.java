package controller;
import command.ICommand;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/controle_pedido")
public class controle_pedido extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");

        try {
            String classPath = "command." + op + "PedidoCommand";

            Class<?> clazz = Class.forName(classPath);
            ICommand command = (ICommand) clazz.getDeclaredConstructor().newInstance();

            String pageDispatcher = command.execute(request, response);

            if (pageDispatcher != null) {
                 request.getRequestDispatcher(pageDispatcher).forward(request, response);
        }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "Erro: " + e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
