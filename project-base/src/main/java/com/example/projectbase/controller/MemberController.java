package com.example.projectbase.controller;

import com.example.projectbase.domain.dto.request.MemberChangePassDto;
import com.example.projectbase.domain.dto.request.MemberCreateDto;
import com.example.projectbase.domain.dto.request.MemberUpdateDto;
import com.example.projectbase.domain.dto.request.NotificationCreateDto;
import com.example.projectbase.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @Tag(name = "member-controller-admin")
    @Operation(summary = "API create member")
    @PostMapping("/member")
    public ResponseEntity<?> create(@RequestBody MemberCreateDto memberCreateDto) {
        return ResponseEntity.ok().body(memberService.createMember(memberCreateDto));
    }

    @Tag(name = "member-controller")
    @Operation(summary = "API get members")
    @GetMapping("/member")
    public ResponseEntity<?> readAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok().body(memberService.getAllMembers(page, size));
    }

    @Tag(name = "member-controller-admin")
    @Operation(summary = "API get member by id")
    @GetMapping("/member/id")
    public ResponseEntity<?> readById(@RequestParam String memberId) throws Exception {
        return ResponseEntity.ok().body(memberService.findMemberById(memberId));
    }

    @Tag(name = "member-controller-admin")
    @Operation(summary = "API get members for admin")
    @GetMapping("/member/admin")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok().body(memberService.getAll(page, size));
    }

    @Tag(name = "member-controller")
    @Operation(summary = "API update member")
    @PutMapping("/member")
    public ResponseEntity<?> update(@RequestParam String memberId, @RequestBody MemberUpdateDto memberUpdateDto) {
        return ResponseEntity.ok().body(memberService.updateMember(memberId, memberUpdateDto));
    }

    @Tag(name = "member-controller")
    @Operation(summary = "API change password")
    @PutMapping("/member/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody MemberChangePassDto memberChangePassDto) {
        return ResponseEntity.ok().body(memberService.changePassword(memberChangePassDto));
    }

    @Tag(name = "member-controller-admin")
    @Operation(summary = "API delete member")
    @DeleteMapping("/member")
    public ResponseEntity<?> deleteNotification(@RequestParam String memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok().build();
    }
}
