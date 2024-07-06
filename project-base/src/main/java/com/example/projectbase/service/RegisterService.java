package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.RegisterRequestDto;
import com.example.projectbase.domain.dto.response.RegisterDto;
import com.example.projectbase.domain.entity.Register;

import java.util.List;

public interface RegisterService {
    RegisterDto register(RegisterRequestDto registerRequestDto);
    List<Register> getAllRegisters(int page, int size);
    List<RegisterDto> findRegistersByName(String name, int page, int size);
    RegisterDto acceptRegister(String id);
    RegisterDto rejectRegister(String id);
    void deleteRegister(String id);
}
