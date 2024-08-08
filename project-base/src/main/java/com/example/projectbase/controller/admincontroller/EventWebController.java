package com.example.projectbase.controller.admincontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventWebController {
    @GetMapping("/admin/web/event")
    public String success() {
        return "pages-event";
    }
}
