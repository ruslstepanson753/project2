package ru.alishev.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        return (List<Person>) session.createQuery("from Person",Person.class).stream().toList();
    }

    public Optional<Person> show(String mail) {
        return null;
    }

    public Person show(Integer id) {
        return null;    }

    public void save(Person person) {

    }

    public void update(int id, Person person) {

    }

    public void delete(int id) {

    }

    public void testMultipleUpdate() {

    }

    private List<Person> generate1000() {
        return null;
    }

    public void testBatchUpdate() {
    }
}
