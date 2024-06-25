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
    public ResponseEntity<?> readGeneral() {
        return ResponseEntity.ok().body(notificationService.getGeneralNotification());
    }

    @Tag(name = "note-controller")
    @Operation(summary = "API get personal notifications")
    @GetMapping("/notification/personal/{memberId}")
    public ResponseEntity<?> readPersonal(@PathVariable String memberId) {
        return ResponseEntity.ok().body(notificationService.getPersonalNotification(memberId));
    }

    @Tag(name = "note-controller-admin")
    @Operation(summary = "API update notification")
    @PutMapping("/notification/{notificationId}")
    public ResponseEntity<?> update(@PathVariable String notificationId, @RequestBody NotificationCreateDto notificationCreateDto) {
        return ResponseEntity.ok().body(notificationService.updateNotification(notificationId, notificationCreateDto));
    }

    @Tag(name = "note-controller-admin")
    @Operation(summary = "API delete notification")
    @DeleteMapping("/notification/{notificationId}")
    public ResponseEntity<?> deleteNotification(@PathVariable String notificationId) {
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.ok().build();
    }
}
