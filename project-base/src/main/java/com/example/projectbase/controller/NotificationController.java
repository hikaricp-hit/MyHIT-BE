package com.example.projectbase.controller;

import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
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
    @PostMapping("/admin/notification/general")
    public ResponseEntity<?> createGeneral(@RequestBody NotificationCreateDto notificationCreateDto) {
        return VsResponseUtil.success(notificationService.createGeneralNotification(notificationCreateDto));
    }

    @Tag(name = "note-controller-admin")
    @Operation(summary = "API create personal notification")
    @PostMapping("/admin/notification/personal/{memberId}")
    public ResponseEntity<?> createPersonal(@RequestBody NotificationCreateDto notificationCreateDto, @PathVariable String memberId) {
        return VsResponseUtil.success(notificationService.createPersonalNotification(notificationCreateDto, memberId));
    }

    @Tag(name = "note-controller")
    @Operation(summary = "API get general notifications")
    @GetMapping("/user/notification/general")
    public ResponseEntity<?> readGeneral(@RequestBody PaginationFullRequestDto paginationFullRequestDto) {
        return VsResponseUtil.success(notificationService.getGeneralNotification(paginationFullRequestDto));
    }

    @Tag(name = "note-controller")
    @Operation(summary = "API get personal notifications")
    @GetMapping("/user/notification/personal")
    public ResponseEntity<?> readPersonal(@RequestParam String memberId, @RequestBody PaginationFullRequestDto paginationFullRequestDto) {
        return VsResponseUtil.success(notificationService.getPersonalNotification(memberId, paginationFullRequestDto));
    }

    @Tag(name = "note-controller-admin")
    @Operation(summary = "API update notification")
    @PutMapping("/admin/notification/{notificationId}")
    public ResponseEntity<?> update(@PathVariable String notificationId, @RequestBody NotificationCreateDto notificationCreateDto) {
        return VsResponseUtil.success(notificationService.updateNotification(notificationId, notificationCreateDto));
    }

    @Tag(name = "note-controller-admin")
    @Operation(summary = "API delete notification")
    @DeleteMapping("/admin/notification/{notificationId}")
    public ResponseEntity<?> deleteNotification(@PathVariable String notificationId) {
        return VsResponseUtil.success(notificationService.deleteNotification(notificationId));
    }
}
