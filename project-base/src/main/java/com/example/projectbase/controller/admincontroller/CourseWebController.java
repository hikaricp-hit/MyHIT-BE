package com.example.projectbase.controller.admincontroller;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.CourseRequestDto;
import com.example.projectbase.domain.dto.request.MemberUpdateDto;
import com.example.projectbase.domain.entity.Course;
import com.example.projectbase.domain.entity.Member;
import com.example.projectbase.domain.entity.Notification;
import com.example.projectbase.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping("/auth/course")
public class CourseWebController {
    @Autowired
    CourseService courseService;

    @GetMapping("")
    public ModelAndView success(Model model) {
        List<Course> list= courseService.getAll();
        model.addAttribute("list", list);
        return new ModelAndView("pages-course");
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute CourseRequestDto courseRequestDto, Model model) {
        courseService.createCourse(courseRequestDto);
        List<Course> list= courseService.getAll();
        model.addAttribute("respone", "Create Course success!");
        model.addAttribute("list", list);
        return new ModelAndView("pages-course");
    }


    @PostMapping("/update")
    public ModelAndView update(@RequestParam String courseUpdateId, @ModelAttribute CourseRequestDto courseUpdateDto, Model model) {
        courseService.updateCourse(courseUpdateId,courseUpdateDto);
        List<Course> list= courseService.getAll();
        model.addAttribute("respone", "Update Course success!");
        model.addAttribute("list", list);
        return new ModelAndView("pages-course");
    }
    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam String courseDeleteId, Model model) {
        courseService.deleteCourse(courseDeleteId);
        List<Course> list= courseService.getAll();
        model.addAttribute("respone", "Delete Course success!");
        model.addAttribute("list", list);
        return new ModelAndView("pages-course");
    }



}
