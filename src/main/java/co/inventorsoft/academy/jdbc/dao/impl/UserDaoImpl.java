package co.inventorsoft.academy.jdbc.dao.impl;

import co.inventorsoft.academy.jdbc.dao.UserDao;
import co.inventorsoft.academy.jdbc.dao.constants.Field;
import co.inventorsoft.academy.jdbc.dao.utils.QueryUtil;
import co.inventorsoft.academy.jdbc.exception.DaoException;
import co.inventorsoft.academy.jdbc.model.User;
import co.inventorsoft.academy.jdbc.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static co.inventorsoft.academy.jdbc.dao.constants.Query.*;

public class UserDaoImpl implements UserDao {

    @Override
    public Long add(User user) throws DaoException {
        long result = -1;

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement prst = QueryUtil.prepareStatement(connection, SQL_USER_CREATE,true,
                     QueryUtil.getFieldsForStatement(getAllFieldsOfObject(user), false))) {
            if (prst.executeUpdate() > 0) {
                ResultSet rs = prst.getGeneratedKeys();
                if (rs.next()) {
                    result = rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }

        return result;

    }

    @Override
    public Optional<User> get(Long id) throws DaoException {
        User user = null;

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement prst = connection.prepareStatement(SQL_USER_GET)) {
            prst.setLong(1, id);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                user = mapEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);

    }

    @Override
    public boolean update(User user) throws DaoException {
        boolean result;

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement prst = QueryUtil.prepareStatement(connection, SQL_USER_UPDATE,
                     false, QueryUtil.getFieldsForStatement(getAllFieldsOfObject(user), true)) )  {
            result = prst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }

        return result;

    }

    @Override
    public boolean delete(Long id) throws DaoException {
        boolean result;

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement prst = connection.prepareStatement(SQL_USER_DELETE)) {
            prst.setLong(1, id);
            result = prst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }

        return result;
    }

    @Override
    public List<User> getAll() throws DaoException {
        List<User> users = new ArrayList<>();

        try (Connection con = ConnectionUtil.getConnection();
             Statement st = con.createStatement();) {

            ResultSet rs = st.executeQuery(SQL_USER_GET_ALL);
            while (rs.next()) {
                users.add(mapEntity(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return users;
    }

    protected User mapEntity(ResultSet rs) throws DaoException {
        try {
            User user = new User();
            user.setId(rs.getLong(Field.USER_ID));
            user.setFirstname(rs.getString(Field.USER_FIRST_NAME));
            user.setLastname(rs.getString(Field.USER_LAST_NAME));
            user.setPhone(rs.getString(Field.USER_PHONE));
            user.setEmail(rs.getString(Field.USER_EMAIL));
            user.setPassword(rs.getString(Field.USER_PASSWORD));
            user.setDescription(rs.getString(Field.USER_DESCRIPTION));

            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected Object[] getAllFieldsOfObject(User user) throws DaoException {
        try {
            return new Object[]{
                    user.getFirstname(),
                    user.getLastname(),
                    user.getPhone(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getDescription(),
                    user.getId(),
            };
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new DaoException("User object is null! Can't get fields!");
        }
    }



}
