package com.example.projectbase.controller;

import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.request.RegisterRequestDto;
import com.example.projectbase.domain.dto.response.RegisterDto;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.service.RegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterService registerService;

    @Tag(name = "register-controller")
    @Operation(summary = "API register course")
    @PostMapping("/user/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto registerRequestDto) {
        try {
            RegisterDto registerDto = registerService.register(registerRequestDto);
            return VsResponseUtil.success(registerDto);
        } catch (ValidationException ex) {
            return VsResponseUtil.error(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundException ex) {
            return VsResponseUtil.error(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return VsResponseUtil.error("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Tag(name = "register-controller")
    @Operation(summary = "API find register by subscriber's name")
    @GetMapping("/user/register/name")
    public ResponseEntity<?> findRegister(@RequestParam String name, @Valid @ParameterObject PaginationFullRequestDto paginationRequestDto) {
        return VsResponseUtil.success(registerService.findRegistersByName(name, paginationRequestDto));
    }

    @Tag(name = "register-controller-admin")
    @Operation(summary = "API get all register")
    @GetMapping("/admin/register")
    public ResponseEntity<?> getAllRegister(@Valid @ParameterObject PaginationFullRequestDto paginationRequestDto) {
        return VsResponseUtil.success(registerService.getAllRegisters(paginationRequestDto));
    }

    @Tag(name = "register-controller-admin")
    @Operation(summary = "API accept register")
    @PutMapping("/admin/register/accept")
    public ResponseEntity<?> acceptRegister(@RequestParam String id) {
        return VsResponseUtil.success(registerService.acceptRegister(id));
    }

    @Tag(name = "register-controller-admin")
    @Operation(summary = "API reject register")
    @PutMapping("/admin/register/reject")
    public ResponseEntity<?> rejectRegister(@RequestParam String id) {
        return VsResponseUtil.success(registerService.rejectRegister(id));
    }

    @Tag(name = "register-controller-admin")
    @Operation(summary = "API delete register")
    @DeleteMapping("/admin/register")
    public ResponseEntity<?> deleteCourse(@RequestParam String registerId) {
        return VsResponseUtil.success(registerService.deleteRegister(registerId));
    }


    @Tag(name = "register-controller-user")
    @Operation(summary = "API user cancel register")
    @DeleteMapping("/user/cancel-register")
    public ResponseEntity<?> cancelRegister(@RequestParam String registerId, @RequestParam String memberId) {
        return VsResponseUtil.success(registerService.cancelRegister(registerId, memberId));
    }

}
