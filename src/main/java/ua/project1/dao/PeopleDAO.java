package ua.project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.project1.mapper.PeopleMapper;
import ua.project1.model.People;
import java.util.List;

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

    public List<People> getPeopleList () {
        return jdbcTemplate.query("SELECT * FROM library.people;", new PeopleMapper());
    }

    public People getPeopleById (int id) {
        return jdbcTemplate.query("SELECT * FROM library.people WHERE id=?;",
                new Object[]{id}, new PeopleMapper()).stream().findAny().orElse(null);
    }

    public void editPeople (People people) {
        jdbcTemplate.update("UPDATE library.people SET `name`=?, `year`=? WHERE id=?;",
                people.getName(), people.getYear(), people.getId());
    }
    public void deletePeople (int id) {
        jdbcTemplate.update("DELETE FROM library.people WHERE id=?", id);
    }


}
