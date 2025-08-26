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



    public BookDAO() {

    }

    public List<Book> getBooks() {
        return null;
    }

    public Book getBook(int id) {
        return null;
    }

    public void save(Book book) {
    }

    public void update(int id, Book book) {
    }

    public List<Book> getBooksByPerson(Person person) {
        return null;
    }
}
