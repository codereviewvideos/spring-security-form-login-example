package uk.co.a6software.spring_security_form_login_example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String index() {
        return "redirect:/public";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/public")
    public String publicHome() {
        return "public";
    }

    @GetMapping("/private")
    public String privateHome() {
        return "private";
    }
}
