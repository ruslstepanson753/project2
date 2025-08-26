package ru.alishev.springcourse.controllers.old;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "surname",required = false)String surname,
            Model model) {
// Правильное создание логгера
        Logger logger = LoggerFactory.getLogger(this.getClass());
        String message = "Hello " + name + " " + surname;
        logger.warn("Name: {}, Surname: {}", name, surname);
        model.addAttribute("message", message);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage(){
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculatorPage(
            @RequestParam("a") int a,
            @RequestParam("b") int b,
            @RequestParam("operation") String operation,
            Model model
    ){
        double resultDouble = switch (operation){
            case "sum" -> b + a;
            case "sub" -> a - b;
            case "mul" -> a * b;
            case "div" -> (double)a / (double)b;
            default -> throw new IllegalArgumentException("Invalid operation");
        };
        String result = a + " " + operation + " " + b + " = " + String.format("%.2f", resultDouble);;
        model.addAttribute("result", result);
        return "first/calculator";
    }
}
