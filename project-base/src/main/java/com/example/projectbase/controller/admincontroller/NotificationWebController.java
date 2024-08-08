package com.example.projectbase.controller.admincontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotificationWebController {
    @GetMapping("/admin/web/notification")
    public String success() {
        return "pages-notification";
    }
}
