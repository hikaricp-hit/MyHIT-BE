package com.example.projectbase.controller;

import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationRequestDto;
import com.example.projectbase.domain.dto.request.RegisterRequestDto;
import com.example.projectbase.service.RegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterService registerService;

    @Tag(name = "register-controller")
    @Operation(summary = "API register course")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto registerRequestDto) {
        return VsResponseUtil.success(registerService.register(registerRequestDto));
    }

    @Tag(name = "register-controller")
    @Operation(summary = "API find register by subscriber's name")
    @GetMapping("/register/name")
    public ResponseEntity<?> findRegister(@RequestParam String name, @RequestBody PaginationRequestDto paginationRequestDto) {
        return VsResponseUtil.success(registerService.findRegistersByName(name, paginationRequestDto));
    }

    @Tag(name = "register-controller-admin")
    @Operation(summary = "API get all register")
    @GetMapping("/register")
    public ResponseEntity<?> getAllRegister(@RequestBody PaginationFullRequestDto paginationRequestDto) {
        return VsResponseUtil.success(registerService.getAllRegisters(paginationRequestDto));
    }

    @Tag(name = "register-controller-admin")
    @Operation(summary = "API accept register")
    @PutMapping("/register/accept")
    public ResponseEntity<?> acceptRegister(@RequestParam String id) {
        return VsResponseUtil.success(registerService.acceptRegister(id));
    }

    @Tag(name = "register-controller-admin")
    @Operation(summary = "API reject register")
    @PutMapping("/register/reject")
    public ResponseEntity<?> rejectRegister(@RequestParam String id) {
        return VsResponseUtil.success(registerService.rejectRegister(id));
    }

    @Tag(name = "register-controller-admin")
    @Operation(summary = "API delete register")
    @DeleteMapping("/register")
    public ResponseEntity<?> deleteCourse(@RequestParam String registerId) {
        return VsResponseUtil.success(registerService.deleteRegister(registerId));
    }
}
