/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package portal;

import database.ConnectionFactory;
import database.beans.User;
import database.exceptions.DAOException;
import database.models.UserDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author joao
 */
@WebServlet(name = "PortalServlet", urlPatterns = {"/PortalServlet"})
public class PortalServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Portal</title>");
            out.println("<link rel=\"stylesheet\"");
            out.println("href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\"");
            out.println("integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\"");
            out.println("crossorigin=\"anonymous\"");
            out.println("/>");
            out.println("<link rel=\"stylesheet\"");
            out.println("href=\"/css/form.css\"");
            out.println("/>");
            out.println("</head>");
            out.println("<body>");

            HttpSession session = request.getSession();
            if(session.getAttribute("logged") == null) {
                RequestDispatcher rd = request.getRequestDispatcher("/ErrorServlet");
                request.setAttribute("error", "Você precisa se autenticar para acessar esta página");
                request.setAttribute("link", "Exercicio3/login/index.html");
                rd.forward(request, response);
                return;
            }

            out.println("<div class=\"container d-flex justify-content-center\">");
            out.println("<form action=\"/Exercicio3/CadastrarUsuarioServlet\" method=\"post\" class=\"d-flex flex-column form\" >");
            out.println("<input class=\"form-control\" type=\"text\" name=\"name\" placeholder=\"name\"/>");
            out.println("<span class=\"error-name error\"></span>");
            out.println("<input class=\"form-control\" type=\"text\" name=\"login\" placeholder=\"login\"/>");
            out.println("<span class=\"error-login error\"></span>");
            out.println("<input class=\"form-control\" type=\"password\" name=\"password\" placeholder=\"password\" />");
            out.println("<span class=\"error-password error\"></span>");
            out.println("<button name=\"enter\" type=\"submit\" class=\"enter btn btn-primary\">Criar</button>");
            out.println("</form>");
            out.println("</div>");

            UserDAO dao = new UserDAO(new ConnectionFactory().getConnection());
            List<User> users = dao.getAll();
            out.println("<div class=\"users\">");
                out.println("<h1>Usuários</h1>");
                out.println("<ul>");
                    for(int i = 0; i < users.size() ; i++)
                    {
                        out.println("<li>");
                        out.println("<p class=\"name\"> Nome: "+ users.get(i).getName() +"</p>");
                        out.println("<p class=\"username\"> Login: "+ users.get(i).getLogin() +"</p>");
                        out.println("<p class=\"password\"> Senha: "+ users.get(i).getPassword() +"</p>");
                        out.println("</li>");
                    }
                out.println("</ul>");
                out.println("<a href=\"/Exercicio3/LogoutServlet\"><button class=\"btn btn-danger\">Logout</button></a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
