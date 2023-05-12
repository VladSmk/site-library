package ua.project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.project1.model.People;

@Component
public class PeopleDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PeopleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add_people (People people) {
        jdbcTemplate.update("INSERT INTO library.people(`name`,`year`) VALUES (?,?);",
                people.getName(), people.getYear());
    }


}
