package co.inventorsoft.academy.jdbc.dao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class QueryUtil {
    private QueryUtil() { }

    public static PreparedStatement prepareStatement(Connection connection, String query, boolean returnGeneratedKeys,
                                                      List<Object> values) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(query,
                returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        setValuesInStatement(statement, values);

        return statement;
    }

    public static void setValuesInStatement(PreparedStatement statement, List<Object> values) throws SQLException {
        for (int i = 0; i < values.size(); i++) {
            statement.setObject(i + 1, values.get(i));
        }
    }

}
