package nl.marcenschede.springtest.parallel;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by marc on 30/12/15.
 */
public class ParallelRowMapper implements RowMapper {
    @Override
    public String mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person(resultSet.getInt("id"), resultSet.getString("name"));

        return person.toString();
    }
}
