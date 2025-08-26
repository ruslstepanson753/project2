package ru.alishev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.dao.BookDAO;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String getBooks(Model model) {
        List<Book> books = bookDAO.getBooks();
        model.addAttribute("books", books);
        return "library/books";
    }

    @GetMapping("/new")
    public String goToNewBook(@ModelAttribute("book") Book book) {
        return "library/newBook";
    }

    @PostMapping()
    public String newBook(@ModelAttribute("book") Book book,Model model) {
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String goToEditBook(Model model, @PathVariable("id") int id) {
        Book book = bookDAO.getBook(id);
        model.addAttribute("book", book);
        return "library/editBook";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") Book book,Model model,@PathVariable("id") int id) {
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String goToBook(@PathVariable("id") int id, Model model) {
        Book book = bookDAO.getBook(id);
        Integer personId = book.getPersonId();

        if (personId != null) {
            model.addAttribute("person", personDAO.show(personId));
        } else {
            model.addAttribute("person", null);
        }

        model.addAttribute("book", book);
        return "library/book";
    }

}
