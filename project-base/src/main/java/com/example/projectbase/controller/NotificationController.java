package com.example.projectbase.controller;

import com.example.projectbase.domain.dto.request.NotificationCreateDto;
import com.example.projectbase.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @Tag(name = "note-controller-admin")
    @Operation(summary = "API create general notification")
    @PostMapping("/notification/general")
    public ResponseEntity<?> createGeneral(@RequestBody NotificationCreateDto notificationCreateDto) {
        return ResponseEntity.ok().body(notificationService.createGeneralNotification(notificationCreateDto));
    }

    @Tag(name = "note-controller-admin")
    @Operation(summary = "API create personal notification")
    @PostMapping("/notification/personal/{memberId}")
    public ResponseEntity<?> createPersonal(@RequestBody NotificationCreateDto notificationCreateDto, @PathVariable String memberId) {
        return ResponseEntity.ok().body(notificationService.createPersonalNotification(notificationCreateDto, memberId));
    }

    @Tag(name = "note-controller")
    @Operation(summary = "API get general notifications")
    @GetMapping("/notification/general")
    public ResponseEntity<?> readGeneral(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok().body(notificationService.getGeneralNotification(page, size));
    }

    @Tag(name = "note-controller")
    @Operation(summary = "API get personal notifications")
    @GetMapping("/notification/personal")
    public ResponseEntity<?> readPersonal(@RequestParam String memberId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok().body(notificationService.getPersonalNotification(memberId, page, size));
    }

    @Tag(name = "note-controller-admin")
    @Operation(summary = "API update notification")
    @PutMapping("/notification/{notificationName}")
    public ResponseEntity<?> update(@PathVariable String notificationName, @RequestBody NotificationCreateDto notificationCreateDto) {
        return ResponseEntity.ok().body(notificationService.updateNotification(notificationName, notificationCreateDto));
    }

    @Tag(name = "note-controller-admin")
    @Operation(summary = "API delete notification")
    @DeleteMapping("/notification/{notificationName}")
    public ResponseEntity<?> deleteNotification(@PathVariable String notificationName) {
        notificationService.deleteNotification(notificationName);
        return ResponseEntity.ok().build();
    }
}
