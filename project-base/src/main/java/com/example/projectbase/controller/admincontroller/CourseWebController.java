package com.example.projectbase.controller.admincontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseWebController {
    @GetMapping("/admin/web/course")
    public String success() {
        return "pages-course";
    }
}
