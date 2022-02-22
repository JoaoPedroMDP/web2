<%-- 
    Document   : MeuTADS
    Created on : Feb 13, 2022, 12:14:03 PM
    Author     : joao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.HashMap" %>
<!DOCTYPE html>
<html>
    <head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="css/meuTads.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Meu TADS</title>
    </head>
    <body>
        <div class="container">
            <div class="headers">
                <img class="small-image" src="assets/Logo_TADS.jpeg"/>
                <h1 class="title">Meu TADS</h1>
            </div>
            <div class="content">
                <table>
                <%
                    HashMap<String, String> members = new HashMap<String, String>() {{
                        put("João", "https://www.instagram.com/johnpedro_mdp/" );
                        put("Tom", "https://www.instagram.com/fuyukitk/");
                        put("Leonardo", "http://Instagram.com/xavierleozin");
                        put("Gustavo", "https://www.instagram.com/gustavoschwanka");
                        put("Ludimilla", "https://www.instagram.com/_ludi_milla_/");
                    }};

                    String[] membersNames = members.keySet().toArray(new String[members.size()]);
                    for( int i = 0; i < members.size(); i++ ) {
                        String name = membersNames[i];
                        String link = members.get(name);
                        out.println("<tr>");
                        out.println("<td class=\"name\">" + name + "</td>");
                        out.println("<td><a href=\""+ link +"\">Rede social</a></td>");
                        out.println("</tr>");
                    }
                %>
                </table>
            </div>
            <div class="footer">
                <a href="SEPT"><button class="btn btn-primary">Voltar</button></a>
            </div>
        </div>
    </body>
</html>
