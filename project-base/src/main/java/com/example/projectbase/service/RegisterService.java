package com.example.projectbase.service;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.RegisterRequestDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.RegisterDto;
import com.example.projectbase.domain.entity.Register;

import java.util.List;

public interface RegisterService {
    RegisterDto register(RegisterRequestDto registerRequestDto);
    PaginationResponseDto<Register> getAllRegisters(PaginationFullRequestDto paginationRequestDto);
    PaginationResponseDto<Register> findRegistersByName(String name, PaginationFullRequestDto paginationRequestDto);
    RegisterDto acceptRegister(String id);
    RegisterDto rejectRegister(String id);
    CommonResponseDto deleteRegister(String id);
    CommonResponseDto cancelRegister(String registerId, String memberId);
}
