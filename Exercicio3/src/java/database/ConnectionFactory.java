package database;


import database.exceptions.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory implements AutoCloseable{

    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/web2?useSSL=false";
    private static String LOGIN = "joao";
    private static String PASSWORD = "4521563";

    private Connection con = null;

    public Connection getConnection() throws DAOException {
        if (con == null) {
            try {
                Class.forName(DRIVER);
                con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new DAOException("Erro ao carregar o driver", e);
            } catch (SQLException e) {
                throw new DAOException("Erro ao conectar no Banco de Dados: " + e.getMessage(), e);
            }
        }

        return con;
    }

    @Override
    public void close() {
        if (con != null){
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Erro ao fechar a conexão.");
                e.printStackTrace();
            }
        }
    }
}
