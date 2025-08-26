package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
        List<Book> books = jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
        return books;
    }

    public Book getBook(int id) {
        return jdbcTemplate.queryForObject("select * from book where book_id = ?", new Object[] { id }, new BeanPropertyRowMapper<Book>(Book.class));
    }

    public void save(Book book) {
        jdbcTemplate.update("insert into book(book_name,author,year_book) values (?,?,?)",
                book.getBookName(), book.getAuthor(), book.getYearBook());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET book_name = ?, author = ?, year_book = ? WHERE book_id = ?",
                book.getBookName(), book.getAuthor(), book.getYearBook(), id);
    }

    public List<Book> getBooksByPerson(Person person) {
        Integer id = person.getPersonId();
        List<Book> books = jdbcTemplate.query("" +
                        "SELECT * FROM book " +
                        "JOIN person ON person.person_id = book.person_id " +
                        "WHERE book.person_id = ?", // Указываем конкретную таблицу
                new Object[] { id },
                new BeanPropertyRowMapper<Book>(Book.class));
        return books;
    }
}
