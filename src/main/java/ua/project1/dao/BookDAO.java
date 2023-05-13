package ua.project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.project1.mapper.BookMapper;
import ua.project1.model.Book;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add_book (Book book) {
        jdbcTemplate.update("INSERT INTO library.books(`name`,`author_id`,`year`) VALUES (?,?,?);",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public List<Book> getBookList () {
        return jdbcTemplate.query("SELECT * FROM library.books;", new BookMapper());
    }
    public Book getBookById (int id) {
        return jdbcTemplate.query("SELECT * FROM library.books WHERE id=?;",
                new Object[]{id}, new BookMapper()).stream().findAny().orElse(null);
    }

    public void editBook (Book book) {
        jdbcTemplate.update("UPDATE library.books SET `name`=?, `year`=?, `author_id`=? WHERE id=?;",
                book.getName(), book.getYear(), book.getAuthor(), book.getId());
    }
    public void deleteBook (int id) {
        jdbcTemplate.update("DELETE FROM library.books WHERE id=?", id);
    }

}
