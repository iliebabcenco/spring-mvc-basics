package org.ilie.springcourse.controllers;


import org.ilie.springcourse.dao.PersonDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO service;

    public PeopleController(PersonDAO service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("peopleList", service.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", service.show(id));
        return "people/show";
    }
}
