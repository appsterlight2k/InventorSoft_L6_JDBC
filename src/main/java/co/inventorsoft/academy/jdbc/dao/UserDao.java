package co.inventorsoft.academy.jdbc.dao;

import co.inventorsoft.academy.jdbc.exception.DaoException;
import co.inventorsoft.academy.jdbc.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User> {
    Long add(User user) throws DaoException;
    Optional<User> get(Long id) throws DaoException;
    boolean update(User user) throws DaoException;
    boolean delete(Long id) throws DaoException;
    List<User> getAll() throws DaoException;
}
