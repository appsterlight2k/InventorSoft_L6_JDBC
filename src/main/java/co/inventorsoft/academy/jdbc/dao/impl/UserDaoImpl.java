package co.inventorsoft.academy.jdbc.dao.impl;

import co.inventorsoft.academy.jdbc.dao.AbstractDao;
import co.inventorsoft.academy.jdbc.dao.UserDao;
import co.inventorsoft.academy.jdbc.dao.constants.Field;
import co.inventorsoft.academy.jdbc.exception.DaoException;
import co.inventorsoft.academy.jdbc.model.User;
import co.inventorsoft.academy.jdbc.connection.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static co.inventorsoft.academy.jdbc.dao.constants.Query.*;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    @Override
    public String getSelectQuery() {
        return SQL_USER_GET;
    }

    @Override
    public String getCreateQuery() {
        return SQL_USER_CREATE;
    }

    @Override
    public String getUpdateQuery() {
        return SQL_USER_UPDATE;
    }

    @Override
    public String getSelectAllQuery() {
        return SQL_USER_GET_ALL;
    }

    @Override
    public String getDeleteQuery() {
        return SQL_USER_DELETE;
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        User user = null;
        try (Connection con = ConnectionManager.getConnection();
            PreparedStatement prst = con.prepareStatement(SQL_USER_GET_BY_EMAIL); ) {
            prst.setString(1, email);
            try (ResultSet rs = prst.executeQuery();) {
                if (rs.next()) {
                    user = mapEntity(rs);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(String.format("Error finding user with email %s", email));
        }

        return Optional.ofNullable(user);
    }

    protected User mapEntity(ResultSet rs) throws DaoException {
        try {
            User user = new User();
            user.setId(rs.getLong(Field.ID));
            user.setFirstname(rs.getString(Field.USER_FIRST_NAME));
            user.setLastname(rs.getString(Field.USER_LAST_NAME));
            user.setPhone(rs.getString(Field.USER_PHONE));
            user.setEmail(rs.getString(Field.USER_EMAIL));
            user.setPassword(rs.getString(Field.USER_PASSWORD));
            user.setDescription(rs.getString(Field.USER_DESCRIPTION));

            return user;
        } catch (SQLException e) {
            throw new DaoException("Error of ResultSet processing! " + e);
        }
    }

    protected List<Object> getEntityFields(User user) throws DaoException {
        if (user == null) {
            throw new DaoException("User object is null! Can't get fields!");
        }

        List<Object> list = new ArrayList<>();
        list.add(user.getFirstname());
        list.add(user.getLastname());
        list.add(user.getPhone());
        list.add(user.getEmail());
        list.add(user.getPassword());
        list.add(user.getDescription());

        Long id = user.getId();
        if (id != null) { // id == null in case of insert operation
            list.add(id);
        }

        return list;
    }

}
