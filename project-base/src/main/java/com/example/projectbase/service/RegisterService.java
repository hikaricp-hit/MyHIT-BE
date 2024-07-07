package com.example.projectbase.service;

import com.example.projectbase.domain.dto.pagination.PaginationRequestDto;
import com.example.projectbase.domain.dto.request.RegisterRequestDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.RegisterDto;
import com.example.projectbase.domain.entity.Register;

import java.util.List;

public interface RegisterService {
    RegisterDto register(RegisterRequestDto registerRequestDto);
    List<Register> getAllRegisters(PaginationRequestDto paginationRequestDto);
    List<RegisterDto> findRegistersByName(String name, PaginationRequestDto paginationRequestDto);
    RegisterDto acceptRegister(String id);
    RegisterDto rejectRegister(String id);
    CommonResponseDto deleteRegister(String id);
}
