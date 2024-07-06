package com.example.projectbase.controller;

import com.example.projectbase.domain.dto.request.CourseRequestDto;
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
        return ResponseEntity.ok().body(registerService.register(registerRequestDto));
    }

    @Tag(name = "register-controller")
    @Operation(summary = "API find register by subscriber's name")
    @GetMapping("/register/name")
    public ResponseEntity<?> findRegister(@RequestParam String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok().body(registerService.findRegistersByName(name, page, size));
    }

    @Tag(name = "register-controller-admin")
    @Operation(summary = "API get all register")
    @GetMapping("/register")
    public ResponseEntity<?> getAllRegister(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok().body(registerService.getAllRegisters(page, size));
    }

    @Tag(name = "register-controller-admin")
    @Operation(summary = "API accept register")
    @PutMapping("/register/accept")
    public ResponseEntity<?> acceptRegister(@RequestParam String id) {
        return ResponseEntity.ok().body(registerService.acceptRegister(id));
    }

    @Tag(name = "register-controller-admin")
    @Operation(summary = "API reject register")
    @PutMapping("/register/reject")
    public ResponseEntity<?> rejectRegister(@RequestParam String id) {
        return ResponseEntity.ok().body(registerService.rejectRegister(id));
    }

    @Tag(name = "register-controller-admin")
    @Operation(summary = "API delete register")
    @DeleteMapping("/register")
    public ResponseEntity<?> deleteCourse(@RequestParam String registerId) {
        registerService.deleteRegister(registerId);
        return ResponseEntity.ok().build();
    }
}
