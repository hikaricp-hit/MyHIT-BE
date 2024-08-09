package com.example.projectbase.controller.admincontroller;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.MemberCreateDto;
import com.example.projectbase.domain.dto.request.MemberUpdateDto;
import com.example.projectbase.domain.entity.Member;
import com.example.projectbase.domain.entity.Register;
import com.example.projectbase.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/auth/register")
public class RegisterWebController {

    @Autowired
    RegisterService registerService;

    @GetMapping("")
    public ModelAndView success(Model model) {
        PaginationResponseDto<Register> list = registerService.getAllRegisters( new PaginationFullRequestDto("","",true,0,1000));
        model.addAttribute("list", list.getItems());
        return new ModelAndView("pages-register");
    }

    @PostMapping("/accept")
    public ModelAndView accept(@RequestParam String acceptRegisterId, Model model) {
        registerService.acceptRegister(acceptRegisterId);
        PaginationResponseDto<Register> list = registerService.getAllRegisters( new PaginationFullRequestDto("","",true,0,1000));
        model.addAttribute("respone", "Register accepted!");
        model.addAttribute("list", list.getItems());
        return new ModelAndView("pages-register");
    }

    @PostMapping("/reject")
    public ModelAndView reject(@RequestParam String rejectRegisterId, Model model) {
        registerService.rejectRegister(rejectRegisterId);
        PaginationResponseDto<Register> list = registerService.getAllRegisters( new PaginationFullRequestDto("","",true,0,1000));
        model.addAttribute("respone", "Register was rejected!");
        model.addAttribute("list", list.getItems());
        return new ModelAndView("pages-register");
    }


    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam String registerDeleteId, Model model) {
        registerService.deleteRegister(registerDeleteId);
        PaginationResponseDto<Register> list = registerService.getAllRegisters( new PaginationFullRequestDto("","",true,0,1000));
        model.addAttribute("respone", "Delete register success!");
        model.addAttribute("list", list.getItems());
        return new ModelAndView("pages-register");
    }
}
