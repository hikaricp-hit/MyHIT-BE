package com.example.projectbase.controller.admincontroller;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.CourseRequestDto;
import com.example.projectbase.domain.dto.request.EventResquestDTO;
import com.example.projectbase.domain.dto.response.EventResponseDto;
import com.example.projectbase.domain.entity.Course;
import com.example.projectbase.domain.entity.Event;
import com.example.projectbase.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/auth/event")
public class EventWebController {
    @Autowired
    EventService eventService;

    @GetMapping("")
    public ModelAndView success(Model model) {
        PaginationResponseDto<Event> list = eventService.getAllEvent(new PaginationFullRequestDto("","",true,0,1000));
        model.addAttribute("list", list.getItems());
        return new ModelAndView("pages-event");
    }

//    @PostMapping("/create")
//    public ModelAndView create(@ModelAttribute CourseRequestDto courseRequestDto, Model model) {
//        eventService.createCourse(courseRequestDto);
//        PaginationResponseDto<EventResponseDto> list = eventService.getAllEvents( new PaginationFullRequestDto("","",true,0,1000));
//        model.addAttribute("respone", "Create Event success!");
//        model.addAttribute("list", list.getItems());
//        return new ModelAndView("pages-event");
//    }

    @PostMapping("/update")
    public ModelAndView update(@RequestParam String eventUpdateId, @ModelAttribute EventResquestDTO eventUpdateDto, Model model) {
        eventService.updateEvent(eventUpdateId,eventUpdateDto);
        PaginationResponseDto<Event> list = eventService.getAllEvent( new PaginationFullRequestDto("","",true,0,1000));
        model.addAttribute("respone", "Update Event success!");
        model.addAttribute("list", list.getItems());
        return new ModelAndView("pages-event");
    }
    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam String eventDeleteId, Model model) {
        eventService.deleteEvent(eventDeleteId);
        PaginationResponseDto<Event> list = eventService.getAllEvent( new PaginationFullRequestDto("","",true,0,1000));
        model.addAttribute("respone", "Delete Event success!");
        model.addAttribute("list", list.getItems());
        return new ModelAndView("pages-event");
    }
}
