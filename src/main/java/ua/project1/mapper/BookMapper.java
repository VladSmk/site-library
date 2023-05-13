package ua.project1.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.project1.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Book(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("author_id"),
                resultSet.getInt("year")
        );
    }
}
