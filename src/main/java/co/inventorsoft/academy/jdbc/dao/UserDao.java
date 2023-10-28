package co.inventorsoft.academy.jdbc.dao;

import co.inventorsoft.academy.jdbc.exception.DaoException;
import co.inventorsoft.academy.jdbc.model.User;

import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> findByEmail(String email) throws DaoException;
}
