package org.ilie.springcourse.controllers;


import org.ilie.springcourse.dao.PersonDAO;
import org.ilie.springcourse.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") Person person) {
        service.save(person);
        return "redirect:/people";
    }

}
