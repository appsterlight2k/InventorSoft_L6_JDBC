package co.inventorsoft.academy.jdbc.dao.utils;

import co.inventorsoft.academy.jdbc.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class QueryUtil {
    private QueryUtil() { }

    public static Object[] getFieldsForStatement(Object[] allFields, boolean isUpdate) throws DaoException {
        if (isUpdate) {
            return allFields;
        } else {
            return Arrays.copyOf(allFields, allFields.length - 1);
        }
    }

    public static PreparedStatement prepareStatement(Connection connection, String query, boolean returnGeneratedKeys,
                                                      Object... values) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(query,
                returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        setValuesInStatement(statement, values);

        return statement;
    }

    public static void setValuesInStatement(PreparedStatement statement, Object... values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            statement.setObject(i + 1, values[i]);
        }

    }

}
