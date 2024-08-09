package com.example.projectbase.controller.admincontroller;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.CourseRequestDto;
import com.example.projectbase.domain.dto.request.MemberUpdateDto;
import com.example.projectbase.domain.entity.Course;
import com.example.projectbase.domain.entity.Member;
import com.example.projectbase.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/auth/course")
public class CourseWebController {
    @Autowired
    CourseService courseService;

    @GetMapping("")
    public ModelAndView success(Model model) {
        PaginationResponseDto<Course> list = courseService.readAllCourse( new PaginationFullRequestDto("","",true,0,1000));
        model.addAttribute("list", list.getItems());
        return new ModelAndView("pages-course");
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute CourseRequestDto courseRequestDto, Model model) {
        courseService.createCourse(courseRequestDto);
        PaginationResponseDto<Course> list = courseService.readAllCourse( new PaginationFullRequestDto("","",true,0,1000));
        model.addAttribute("respone", "Create Course success!");
        model.addAttribute("list", list.getItems());
        return new ModelAndView("pages-course");
    }

    @PostMapping("/update")
    public ModelAndView update(@RequestParam String courseUpdateId, @ModelAttribute CourseRequestDto courseUpdateDto, Model model) {
        courseService.updateCourse(courseUpdateId,courseUpdateDto);
        PaginationResponseDto<Course> list = courseService.readAllCourse( new PaginationFullRequestDto("","",true,0,1000));
        model.addAttribute("respone", "Update Course success!");
        model.addAttribute("list", list.getItems());
        return new ModelAndView("pages-course");
    }
    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam String courseDeleteId, Model model) {
        courseService.deleteCourse(courseDeleteId);
        PaginationResponseDto<Course> list = courseService.readAllCourse( new PaginationFullRequestDto("","",true,0,1000));
        model.addAttribute("respone", "Delete Course success!");
        model.addAttribute("list", list.getItems());
        return new ModelAndView("pages-course");
    }



}
