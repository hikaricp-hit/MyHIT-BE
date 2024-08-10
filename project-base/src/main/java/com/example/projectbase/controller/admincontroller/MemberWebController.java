package com.example.projectbase.controller.admincontroller;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.MemberCreateDto;
import com.example.projectbase.domain.dto.request.MemberUpdateDto;
import com.example.projectbase.domain.entity.Course;
import com.example.projectbase.domain.entity.Member;
import com.example.projectbase.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/auth/member")
public class MemberWebController {
    @Autowired
    MemberService memberService;

    @GetMapping("")
    public ModelAndView success(Model model) {
        List<Member> list= memberService.getAlls();
        model.addAttribute("list", list);
        return new ModelAndView("pages-member");
    }
    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute MemberCreateDto memberCreateDto, Model model) {
        memberService.createMember(memberCreateDto);
        List<Member> list= memberService.getAlls();
        model.addAttribute("respone", "Create member success!");
        model.addAttribute("list", list);
        return new ModelAndView("pages-member");
    }
    @PostMapping("/update")
    public ModelAndView update(@RequestParam String memberUpdateId,@ModelAttribute MemberUpdateDto memberUpdateDto, Model model) {
        memberService.updateMember(memberUpdateId,memberUpdateDto);
        List<Member> list= memberService.getAlls();
        model.addAttribute("respone", "Update member success!");
        model.addAttribute("list", list);
        return new ModelAndView("pages-member");
    }
    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam String memberDeleteId, Model model) {
        memberService.deleteMember(memberDeleteId);
        List<Member> list= memberService.getAlls();
        model.addAttribute("respone", "Delete member success!");
        model.addAttribute("list", list);
        return new ModelAndView("pages-member");
    }
}
