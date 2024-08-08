package com.example.projectbase.controller.admincontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterWebController {
    @GetMapping("/auth/register")
    public String success() {
        return "pages-register";
    }
}
