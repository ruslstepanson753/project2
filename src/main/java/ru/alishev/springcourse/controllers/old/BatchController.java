package ru.alishev.springcourse.controllers.old;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alishev.springcourse.dao.PersonDAO;

@Controller
@RequestMapping("test-batch-update")
public class BatchController {
    private final PersonDAO personDAO;

    public BatchController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String testBatch() {
        return "batch/index";
    }

    @GetMapping("/without")
    public String testBatchWithout() {
        personDAO.testMultipleUpdate();
        return "redirect:/people";
    }

    @GetMapping("/with")
    public String testBatchWith() {
        personDAO.testBatchUpdate();
        return "redirect:/people";
    }
}
