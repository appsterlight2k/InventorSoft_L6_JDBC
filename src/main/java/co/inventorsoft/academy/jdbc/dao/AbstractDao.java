package co.inventorsoft.academy.jdbc.dao;

import co.inventorsoft.academy.jdbc.connection.ConnectionManager;
import co.inventorsoft.academy.jdbc.dao.utils.QueryUtil;
import co.inventorsoft.academy.jdbc.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> implements Dao<T> {
    public abstract String getSelectQuery();
    public abstract String getCreateQuery();
    public abstract String getUpdateQuery();
    public abstract String getSelectAllQuery();
    public abstract String getDeleteQuery();
    protected abstract T mapEntity(ResultSet rs) throws DaoException;
    protected abstract List<Object> getEntityFields(T entity) throws DaoException;


    public Long add(T object) throws DaoException {
        long result = -1;
        String query = getCreateQuery();

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement prst = QueryUtil.prepareStatement(connection, query,true,
                     getEntityFields(object))) {
            if (prst.executeUpdate() > 0) {
                ResultSet rs = prst.getGeneratedKeys();
                if (rs.next()) {
                    result = rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return result;
    }

    public Optional<T> get(Long id) throws DaoException {
        T object = null;
        String query = getSelectQuery();

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement prst = connection.prepareStatement(query)) {
            prst.setLong(1, id);
            try (ResultSet rs = prst.executeQuery();) {
                if (rs.next()) {
                    object = mapEntity(rs);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return Optional.ofNullable(object);
    }

    @Override
    public boolean update(T object) throws DaoException {
        boolean result;
        String query = getUpdateQuery();

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement prst = QueryUtil.prepareStatement(connection, query,
                     false, getEntityFields(object)) )  {
            result = prst.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return result;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        boolean result;
        String query = getDeleteQuery();

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement prst = connection.prepareStatement(query)) {
            prst.setLong(1, id);
            result = prst.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return result;
    }

    @Override
    public List<T> getAll() throws DaoException {
        List<T> users = new ArrayList<>();
        String query = getSelectAllQuery();

        try (Connection con = ConnectionManager.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query);) {
            while (rs.next()) {
                users.add(mapEntity(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return users;
    }

}
