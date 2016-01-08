package nl.marcenschede.springtest.error;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by marc on 08/01/16.
 */
public class NumberRowMapper implements RowMapper<Integer> {

    @Override
    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {

        return resultSet.getInt("NUMBER");
    }
}
