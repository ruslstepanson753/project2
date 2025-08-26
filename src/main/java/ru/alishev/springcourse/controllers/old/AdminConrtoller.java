package ru.alishev.springcourse.controllers.old;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Person;

import java.util.logging.Logger;

@Controller
@RequestMapping("/admin")
public class AdminConrtoller {

    private final PersonDAO personDAO;

    @Autowired
    public AdminConrtoller(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("")
    public String getAdminPage(Model model) {
        model.addAttribute("people", personDAO.index()); // список всех людей
        model.addAttribute("person", new Person()); // пустой объект для формы
        return "adminPage";
    }

    @PatchMapping("/add")
    public String addPerson(@ModelAttribute("person") Person person) {
        Logger logger = Logger.getLogger(this.getClass().getName());
//        logger.warning("person.getId()" + person.getId());
        return "redirect:/people";
    }

}
