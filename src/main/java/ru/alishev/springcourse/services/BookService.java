package ru.alishev.springcourse.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.repositories.BookRepository;
import ru.alishev.springcourse.repositories.PeopleRepository;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BookService(BookRepository bookRepository, PeopleRepository peopleRepository) {
        this.bookRepository = bookRepository;
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public List<Book> getBooksByPerson(Person person) {
        List<Book> books = person.getBooks();
        Hibernate.initialize(books); // Инициализация в той же транзакции
        return books;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    public Book getBook(int id) {
        return bookRepository.findById(id).get();
    }

    @Transactional
    public void update(int id, Book book) {
        book.setBookId(id);
        bookRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getBooks(Integer page, Integer booksPerPage, boolean sortByYear) {
        if (page == null) page = 0;
        if (booksPerPage == null) booksPerPage = 10;

        PageRequest pageRequest;

        if (sortByYear) {
            pageRequest = PageRequest.of(page, booksPerPage, Sort.by("yearBook"));
        } else {
            pageRequest = PageRequest.of(page, booksPerPage);
        }

        return bookRepository.findAll(pageRequest).getContent();
    }

}
