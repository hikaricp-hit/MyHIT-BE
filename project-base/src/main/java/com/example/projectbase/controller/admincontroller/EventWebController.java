package com.example.projectbase.controller.admincontroller;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.CourseRequestDto;
import com.example.projectbase.domain.dto.request.EventResquestDTO;
import com.example.projectbase.domain.dto.request.NotificationCreateDto;
import com.example.projectbase.domain.dto.response.EventResponseDto;
import com.example.projectbase.domain.entity.Course;
import com.example.projectbase.domain.entity.Event;
import com.example.projectbase.domain.entity.Notification;
import com.example.projectbase.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/auth/event")
public class EventWebController {
    @Autowired
    EventService eventService;

    @GetMapping("")
    public ModelAndView success(Model model) {
        List<Event> list = eventService.getAll();
        model.addAttribute("list", list);
        return new ModelAndView("pages-event");
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute EventResquestDTO eventResquestDTO, @RequestParam String type, Model model) {
        if(type.equals("Offline"))
            eventService.createOfflineEvent(eventResquestDTO);
        else if(type.equals("Activity"))
            eventService.createActivityEvent(eventResquestDTO);
        else if(type.equals("Class"))
            eventService.createClassEvent(eventResquestDTO);
        List<Event> list = eventService.getAll();
        model.addAttribute("list", list);
        model.addAttribute("respone", "Create event success!");
        return new ModelAndView("pages-event");
    }

    @PostMapping("/update")
    public ModelAndView update(@RequestParam String eventUpdateId, @ModelAttribute EventResquestDTO eventUpdateDto, Model model) {
        eventService.updateEvent(eventUpdateId,eventUpdateDto);
        List<Event> list = eventService.getAll();
        model.addAttribute("respone", "Update event success!");
        model.addAttribute("list", list);
        return new ModelAndView("pages-event");
    }
    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam String eventDeleteId, Model model) {
        eventService.deleteEvent(eventDeleteId);
        List<Event> list = eventService.getAll();
        model.addAttribute("respone", "Delete event success!");
        model.addAttribute("list", list);
        return new ModelAndView("pages-event");
    }
}
