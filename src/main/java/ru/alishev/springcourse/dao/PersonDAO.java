package ru.alishev.springcourse.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> show(String mail) {
        try {
            Person person = jdbcTemplate
                    .queryForObject("select * from person where email = ?",
                            new Object[] { mail },
                            new PersonMapper());
            return Optional.ofNullable(person);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty(); // Возвращаем пустой Optional если запись не найдена
        }
    }

    public Person show(Integer id) {
        return jdbcTemplate.queryForObject("select * from person where person_id = ?", new Object[] { id }, new BeanPropertyRowMapper<Person>(Person.class));
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person(full_name,year_born) values (?,?)",
                person.getFullName(), person.getYearBorn());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE person SET full_name = ?, year_born = ? WHERE person_id = ?",
                person.getFullName(), person.getYearBorn(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE person_id = ?",id);
    }

    public void testMultipleUpdate() {
        List<Person> people = generate1000();
        long before = System.currentTimeMillis();
        for (Person person : people) {
            save(person);
        }
        long after = System.currentTimeMillis();
        Logger logger = LoggerFactory.getLogger(this.getClass());
        long elapsed = after - before;
        String string = "time: " + elapsed;
        logger.warn(string);
    }

    private List<Person> generate1000() {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
//            people.add(new Person(i+3000,"test"+i,i,"test"+i+"@mail.ru"));
        }
        return people;
    }

    public void testBatchUpdate() {
        List<Person> people = generate1000();
        long before = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("insert into person(name, age, email) values (?,?,?)",
                new BatchPreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        // Указываем только те поля, которые нужны (без id)
//                        ps.setString(1, people.get(i).getName());    // первый параметр
//                        ps.setInt(2, people.get(i).getAge());        // второй параметр
//                        ps.setString(3, people.get(i).getEmail());   // третий параметр
                    }

                    @Override
                    public int getBatchSize() {
                        return people.size(); // правильный размер батча
                    }
                });

        long after = System.currentTimeMillis();
        Logger logger = LoggerFactory.getLogger(this.getClass());
        long elapsed = after - before;
        String string = "time: " + elapsed;
        logger.warn(string);
    }
}
