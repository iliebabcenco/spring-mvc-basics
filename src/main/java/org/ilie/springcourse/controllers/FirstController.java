package org.ilie.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surName
    ) {
        System.out.println(name);
        System.out.println(surName);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage(HttpServletRequest request) {
        System.out.println(request.getParameter("name"));
        return "first/goodbye";
    }
}
