package com.example.projectbase.controller;

import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.request.MemberChangePassDto;
import com.example.projectbase.domain.dto.request.MemberCreateDto;
import com.example.projectbase.domain.dto.request.MemberUpdateDto;
import com.example.projectbase.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @Tag(name = "member-controller-admin")
    @Operation(summary = "API create member")
    @PostMapping("/admin/member")
    public ResponseEntity<?> create(@RequestBody MemberCreateDto memberCreateDto) {
        return VsResponseUtil.success(memberService.createMember(memberCreateDto));
    }

    @Tag(name = "member-controller")
    @Operation(summary = "API get members")
    @GetMapping("/user/member")
    public ResponseEntity<?> readAll(@Valid @ParameterObject PaginationFullRequestDto paginationFullRequestDto) {
        return VsResponseUtil.success(memberService.getAllMembers(paginationFullRequestDto));
    }

    @Tag(name = "member-controller-admin")
    @Operation(summary = "API get member by id")
    @GetMapping("/admin/member/id")
    public ResponseEntity<?> readById(@RequestParam String memberId) throws Exception {
        return VsResponseUtil.success(memberService.findMemberById(memberId));
    }

    @Tag(name = "member-controller-admin")
    @Operation(summary = "API get members for admin")
    @GetMapping("/admin/member/admin")
    public ResponseEntity<?> getAll(@Valid @ParameterObject PaginationFullRequestDto paginationFullRequestDto) {
        return VsResponseUtil.success(memberService.getAll(paginationFullRequestDto));
    }

    @Tag(name = "member-controller")
    @Operation(summary = "API update member")
    @PutMapping("/user/member")
    public ResponseEntity<?> update(@RequestParam String memberId, @RequestBody MemberUpdateDto memberUpdateDto) {
        return VsResponseUtil.success(memberService.updateMember(memberId, memberUpdateDto));
    }

    @Tag(name = "member-controller")
    @Operation(summary = "API change password")
    @PutMapping("/user/member/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody MemberChangePassDto memberChangePassDto) {
        return VsResponseUtil.success(memberService.changePassword(memberChangePassDto));
    }

    @Tag(name = "member-controller-admin")
    @Operation(summary = "API delete member")
    @DeleteMapping("/admin/member")
    public ResponseEntity<?> deleteNotification(@RequestParam String memberId) {
        return VsResponseUtil.success(memberService.deleteMember(memberId));
    }
}
