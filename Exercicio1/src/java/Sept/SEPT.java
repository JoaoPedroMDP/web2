/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Sept;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joao
 */
@WebServlet(name = "SEPT", urlPatterns = {"/SEPT"})
public class SEPT extends HttpServlet {

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
            // Declara uma lista de cursos
            HashMap<String, String> courses = new HashMap<String, String>(){{
                put("TPG - Petróleo e gás", "http://www.sept.ufpr.br/portal/petroleogas/sobre-o-curso/");
                put("TACS - Agente Comunitário de Saúde", "http://www.sept.ufpr.br/portal/agentesaude/sobre-o-curso/");
                put("TADS - Análise e Desenvolvimento de Sistemas", "http://www.sept.ufpr.br/portal/analisesistemas/sobre-o-curso/");
                put("TCI - Comunicacao Institucional", "http://www.sept.ufpr.br/portal/comunicacaoinstitucional/sobre-o-curso/");
                put("TGQ - Gestão da Qualidade", "http://www.sept.ufpr.br/portal/gestaoqualidade/");
                put("TGP - Gestão Pública", "http://www.sept.ufpr.br/portal/gestaopublica/");
                put("TL - Luteria", "http://www.sept.ufpr.br/portal/luteria/sobre-o-curso/");
                put("TNI - Negócios Imobiliários", "http://www.sept.ufpr.br/portal/negociosimobiliarios/sobre-o-curso/");
                put("TPC - Produção Cênica", "http://www.sept.ufpr.br/portal/producaocenica/sobre-o-curso/");
                put("TS - Secretariado", "http://www.sept.ufpr.br/portal/secretariado/sobre-o-curso/");
                put("Especialização em Inteligência Artificial", "http://www.iaa.ufpr.br/");
                put("Especialização em Engenharia de Software", "http://www.engenhariadesoftware.ufpr.br:28080/engenhariadesoftware/");
                put("MBA em Mercado Imobiliário", "http://bit.ly/mbaMERCADOimobiliario");
                put("Pós graduação em Bioinformática", "http://www.bioinfo.ufpr.br/");
            }};

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            // Link sept.css, located  in web/css
            out.println("<link rel=\"stylesheet\" href=\"css/sept.css\">");
            out.println("<title>SEPT</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 class=\"title\">Setor de Educação Profissional e Tecnológica</h1>");
            out.println("<h2 class=\"address\">Rua Dr. Alcides Vieira Arcoverde, 1225</h2>");

            // cria um array contendo todas as keys em cursos
            String[] coursesNames = courses.keySet().toArray(new String[courses.size()]);
            out.println("<table class=\"courses\">");
            for( int i = 0; i < coursesNames.length; i++) {
                out.println("<tr>");
                out.println("<td class=\"course\">" + coursesNames[i] + "</td>");
                String link = courses.get(coursesNames[i]);
                out.println("<td><a href="+ link +">" + link + "</a></td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
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
