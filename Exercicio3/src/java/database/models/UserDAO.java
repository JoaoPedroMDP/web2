package database.models;

import database.QueryFactory;
import database.beans.User;
import database.exceptions.DAOException;
import database.interfaces.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDAO extends QueryFactory implements DAO<User> {

    private Connection con;
    private final String tableName;
    private ArrayList<String> columns;

    public UserDAO(Connection con) throws DAOException {
        if(con == null){
            throw new DAOException("Conexão nula ao criar UserDAO");
        }
        this.con = con;
        this.tableName = "users";
        this.columns = new ArrayList<String>(){{
            add("login");
            add("password");
            add("name");
        }};
    }

    @Override
    public User get(long id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> getAll() throws DAOException {
        List<User> results = new ArrayList<>();
        String query = mount_select(tableName);
        try(PreparedStatement stmt = con.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                User user = new User();
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                results.add(user);
            }
        }catch(SQLException e){
            throw new DAOException("Erro ao buscar todos os usuários", e);
        }

        return results;
    }

    public User getByLogin(String login) throws DAOException {
        HashMap<String, String> params = new HashMap<String, String>(){{
            put("login", login);
        }};
        String query = mount_select(tableName, params);

        try(PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1, login);
            User user = new User();
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                return user;
            }else{
                return null;
            }
        }catch(SQLException e){
            throw new DAOException("Erro ao buscar usuário", e);
        }
    }

    @Override
    public void insert(User user) throws DAOException {
        String query = mount_insert(tableName, columns);
        try (PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.executeUpdate();
        }catch(SQLException e){
            throw new DAOException("Erro ao inserir usuário: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(User user) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(User user) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
