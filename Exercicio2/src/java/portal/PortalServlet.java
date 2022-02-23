/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package portal;

import login.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joao
 */
@WebServlet(name = "PortalServlet", urlPatterns = {"/PortalServlet"})
public class PortalServlet extends HttpServlet {

    private List<Usuario> createUsers() {
        Usuario user1 = new Usuario("João", "userJoao", "SenhaJoão");
        Usuario user2 = new Usuario("Maria", "userMaria", "SenhaMaria");
        Usuario user3 = new Usuario("Rumplestieltskien", "userRumplestieltskien", "SenhaRumplestieltskien");
        List<Usuario> users = new ArrayList<Usuario>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        return users;
    }

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
        List<Usuario> users = (ArrayList<Usuario>) request.getAttribute("list");

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

            out.println("<div class=\"container d-flex justify-content-center\">");
                out.println("<form action=\"/Exercicio2/CadastrarUsuarioServlet\" method=\"post\" class=\"d-flex flex-column form\" >");
                    out.println("<input class=\"form-control\" type=\"text\" name=\"name\" placeholder=\"name\"/>");
                    out.println("<span class=\"error-name error\"></span>");
                    out.println("<input class=\"form-control\" type=\"text\" name=\"username\" placeholder=\"username\"/>");
                    out.println("<span class=\"error-username error\"></span>");
                    out.println("<input class=\"form-control\" type=\"password\" name=\"password\" placeholder=\"password\" />");
                    out.println("<span class=\"error-password error\"></span>");
                    out.println("<button name=\"enter\" type=\"submit\" class=\"enter btn btn-primary\">Criar</button>");
                out.println("</form>");
            out.println("</div>");

            out.println("<div class=\"users\">");
                out.println("<h1>Usuários</h1>");
                out.println("<ul>");
                    for(int i = 0; i < users.size() ; i++)
                    {
                        out.println("<li>");
                        out.println("<p class=\"name\"> Nome: "+ users.get(i).name +"</p>");
                        out.println("<p class=\"username\"> Username: "+ users.get(i).username +"</p>");
                        out.println("<p class=\"password\"> Senha: "+ users.get(i).password +"</p>");
                        out.println("</li>");
                    }
                out.println("</ul>");
                out.println("<a href=\"/Exercicio2/LogoutServlet\"></a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

            request.setAttribute("userList", users);
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
