package tests;

import database.ConnectionFactory;
import database.beans.User;
import database.exceptions.DAOException;
import database.models.UserDAO;

import java.util.HashMap;
import java.util.List;

public class DAOTest {
    public static void main(String[] args) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            UserDAO dao = new UserDAO(factory.getConnection());
            User user = dao.getByLogin("login");
            if(user != null) {
                System.out.println(user.toString());
            }else{
                System.out.println("User not found");
            }
        }catch(DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
