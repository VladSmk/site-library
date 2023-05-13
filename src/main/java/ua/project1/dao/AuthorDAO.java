package ua.project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.project1.mapper.AuthorMapper;
import ua.project1.model.Author;

import java.util.List;

@Component
public class AuthorDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public AuthorDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Author> getAuthorList () {
        return jdbcTemplate.query("SELECT * FROM library.author;", new AuthorMapper());
    }
}
