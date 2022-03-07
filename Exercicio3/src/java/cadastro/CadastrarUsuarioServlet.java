/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cadastro;

import database.ConnectionFactory;
import database.beans.User;
import database.exceptions.DAOException;
import database.models.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author joao
 */
@WebServlet(name = "CadastrarUsuarioServlet", urlPatterns = {"/CadastrarUsuarioServlet"})
public class CadastrarUsuarioServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(
            HttpServletRequest request,
            HttpServletResponse response
    )throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("logged") == null) {
            RequestDispatcher rd = request.getRequestDispatcher("/ErrorServlet");
            request.setAttribute("error", "Você precisa se autenticar para acessar esta página");
            request.setAttribute("link", "Exercicio3/login/index.html");
            rd.forward(request, response);
            return;
        }

        response.setContentType("text/html;charset=UTF-8");
        User user = new User();
        System.out.println(request.getParameter("name"));
        System.out.println(request.getParameter("login"));
        System.out.println(request.getParameter("password"));

        user.setName(request.getParameter("name"));
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));

        try {
            UserDAO dao = new UserDAO(new ConnectionFactory().getConnection());
            dao.insert(user);
            dao.closeConnection();
            RequestDispatcher view = request.getRequestDispatcher("/PortalServlet");
            view.forward(request, response);
        } catch (DAOException e) {
            System.out.println(e.getMessage());
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
