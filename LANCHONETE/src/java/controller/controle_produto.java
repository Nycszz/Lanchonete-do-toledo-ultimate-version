package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.ICommand;

@WebServlet(name = "controle_produto", urlPatterns = {"/controle_produto"})
public class controle_produto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            String op = request.getParameter("op");

            try {
                String classPath = "command." + op + "Command";

                Class<?> commandClass = Class.forName(classPath);
                ICommand commandAction = (ICommand) commandClass.getDeclaredConstructor().newInstance();

               String pageDispatcher = commandAction.execute(request, response);    

                if (pageDispatcher != null) {
                 request.getRequestDispatcher(pageDispatcher).forward(request, response);
        }

         } catch (Exception e) {
    e.printStackTrace();
    request.setAttribute("msg", "Erro ao executar operação: " + e.getMessage());
    request.getRequestDispatcher("erro.jsp").forward(request, response);
}
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controller de Produtos - Lanchonete do Toledo";
    }
}