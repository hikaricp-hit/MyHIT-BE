//package com.example.projectbase.controller.admincontroller;
//
//import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
//import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
//import com.example.projectbase.domain.dto.request.MemberCreateDto;
//import com.example.projectbase.domain.dto.request.MemberUpdateDto;
//import com.example.projectbase.domain.dto.request.NotificationCreateDto;
//import com.example.projectbase.domain.entity.Member;
//import com.example.projectbase.domain.entity.Notification;
//import com.example.projectbase.service.NotificationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//@RestController
//@RequestMapping("/auth/notification")
//public class NotificationWebController {
//
//    @Autowired
//    NotificationService notificationService;
//
//    @GetMapping("")
//    public ModelAndView success(Model model) {
//        PaginationResponseDto<Notification> list = notificationService.getAll( new PaginationFullRequestDto("","",true,0,1000));
//        model.addAttribute("list", list.getItems());
//        return new ModelAndView("pages-notification");
//    }
//    @PostMapping("/create")
//    public ModelAndView create(@ModelAttribute NotificationCreateDto notificationCreateDto, Model model) {
//        notificationService.createMember(notificationCreateDto);
//        PaginationResponseDto<Notification> list = notificationService.getAll( new PaginationFullRequestDto("","",true,0,1000));
//        model.addAttribute("respone", "Create notification success!");
//        model.addAttribute("list", list.getItems());
//        return new ModelAndView("pages-notification");
//    }
//    @PostMapping("/update")
//    public ModelAndView update(@RequestParam String memberUpdateId, @ModelAttribute MemberUpdateDto memberUpdateDto, Model model) {
//        notificationService.updateMember(memberUpdateId,memberUpdateDto);
//        PaginationResponseDto<Notification> list = notificationService.getAll( new PaginationFullRequestDto("","",true,0,1000));
//        model.addAttribute("respone", "Update notification success!");
//        model.addAttribute("list", list.getItems());
//        return new ModelAndView("pages-notification");
//    }
//    @PostMapping("/delete")
//    public ModelAndView delete(@RequestParam String memberDeleteId, Model model) {
//        notificationService.deleteMember(memberDeleteId);
//        PaginationResponseDto<Notification> list = notificationService.getAll( new PaginationFullRequestDto("","",true,0,1000));
//        model.addAttribute("respone", "Delete notification success!");
//        model.addAttribute("list", list.getItems());
//        return new ModelAndView("pages-notification");
//    }
//}
