package com.example.projectbase.controller.admincontroller;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.MemberCreateDto;
import com.example.projectbase.domain.dto.request.MemberUpdateDto;
import com.example.projectbase.domain.dto.request.NotificationCreateDto;
import com.example.projectbase.domain.dto.response.NotificationDto;
import com.example.projectbase.domain.entity.Member;
import com.example.projectbase.domain.entity.Notification;
import com.example.projectbase.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/auth/notification")
public class NotificationWebController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("")
    public ModelAndView success(Model model) {
        List<Notification> list= notificationService.getAll();
        model.addAttribute("list", list);
        return new ModelAndView("pages-notification");
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute NotificationCreateDto notificationCreateDto, @RequestParam String type, @RequestParam String noteMemberId, Model model) {
        if(type.equals("General"))
            notificationService.createGeneralNotification(notificationCreateDto);
        else
            notificationService.createPersonalNotification(notificationCreateDto,noteMemberId);
        List<Notification> list= notificationService.getAll();
        model.addAttribute("list", list);
        model.addAttribute("respone", "Create notification success!");
        return new ModelAndView("pages-notification");
    }

    @PostMapping("/update")
    public ModelAndView update(@RequestParam String noteId, @ModelAttribute NotificationCreateDto notificationCreateDto, Model model) {
        notificationService.updateNotification(noteId, notificationCreateDto);
        List<Notification> list= notificationService.getAll();
        model.addAttribute("list", list);
        model.addAttribute("respone", "Update notification success!");
        return new ModelAndView("pages-notification");
    }

    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam String notificationId, Model model) {
        notificationService.deleteNotification(notificationId);
        List<Notification> list= notificationService.getAll();
        model.addAttribute("list", list);
        model.addAttribute("respone", "Delete notification success!");
        return new ModelAndView("pages-notification");
    }
}
