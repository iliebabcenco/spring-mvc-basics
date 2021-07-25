package org.ilie.springcourse.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/second")
public class SecondController {

    @GetMapping("/calculator")
    public String calc(
            @RequestParam(value = "a", required = false) int a,
            @RequestParam(value = "b", required = false) int b,
            @RequestParam(value = "action", required = false) String action,
            Model model
    ) {
        float result = 0;
        switch (action) {
            case "multiplication":
                result = a * b;
                break;
            case "addition":
                result = a + b;
                break;
            case "subtraction":
                result = a - b;
                break;
            case "division":
                result = a / (float) b;
                break;
        }
        model.addAttribute("result", a +" "+ action+" " + b +" = "+result);

        return "second/calc";
    }

    @GetMapping("/exit")
    public String exit() {
        return "second/exit";
    }
}
