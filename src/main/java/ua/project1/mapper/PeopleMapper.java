package ua.project1.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.project1.model.People;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PeopleMapper implements RowMapper<People> {
    @Override
    public People mapRow(ResultSet resultSet, int i) throws SQLException {
        return new People(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("year")
        );
    }
}
