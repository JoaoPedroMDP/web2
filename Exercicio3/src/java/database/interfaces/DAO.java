package database.interfaces;

import database.exceptions.DAOException;

import java.util.List;

public interface DAO<T> {
    T get(long id) throws DAOException;
    List<T> getAll() throws DAOException;
    void insert(T t) throws DAOException;
    void update(T t) throws DAOException;
    void delete(T t) throws DAOException;
    void closeConnection();
}
