package com.example.projectbase.controller.admincontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberWebController {
    @GetMapping("/admin/web/member")
    public String success() {
        return "pages-member";
    }
}
