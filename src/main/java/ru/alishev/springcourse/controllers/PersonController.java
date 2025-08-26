package ru.alishev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.dao.BookDAO;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.models.Book;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    @Autowired
    public PersonController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String peopleShow(Model model) {
        List<Person> people = personDAO.index();
        model.addAttribute("people", people);
        return "library/people";
    }

    @GetMapping("/new")
    public String goToNewPerson(@ModelAttribute("person") Person person) {
        return "library/newPerson";
    }

    @PostMapping()
    public String newPerson(@ModelAttribute("person") Person person,Model model) {
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String goToEditPerson(Model model, @PathVariable("id") int id) {
        Person person = personDAO.show(id);
        model.addAttribute("person", person);
        return "library/editPerson";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") Person person,Model model,@PathVariable("id") int id) {
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String goToPerson(@PathVariable("id") int id, Model model) {
        Person person = personDAO.show(id);
        List<Book> books = bookDAO.getBooksByPerson(person);
        model.addAttribute("person", person);
        model.addAttribute("books", books);
        return "library/person";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }

}
