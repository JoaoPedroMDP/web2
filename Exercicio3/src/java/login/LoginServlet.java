/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package login;

import database.ConnectionFactory;
import database.beans.User;
import database.exceptions.DAOException;
import database.exceptions.LoginException;
import database.models.UserDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author joao
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        String login = request.getParameter("username");
        String password = request.getParameter("password");

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login efetuado</title>");
        out.println("</head>");
        out.println("<body>");
        try {
            User user = new UserDAO(new ConnectionFactory().getConnection()).getByLogin(login);
            if(user != null && user.getPassword().equals(password) || userAlreadyLogged(request)){
                RequestDispatcher view = request.getRequestDispatcher("/PortalServlet");
                request.getSession().setAttribute("logged", login);
                view.forward(request, response);
            }else{
                throw new LoginException("Usuário ou senha inválidos");
            }
        } catch (DAOException e) {
            out.println("<h1>Erro com o banco de dados</h1>");
        } catch (LoginException e) {
            out.println("<h1>Problema ao logar</h1>");
            out.println("<a href=\"/Exercicio3/login/index.html\">Portal</a>");
        }
        out.println("</body>");
    }

    private boolean userAlreadyLogged(HttpServletRequest request) {
        return request.getSession().getAttribute("user") != null;
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
