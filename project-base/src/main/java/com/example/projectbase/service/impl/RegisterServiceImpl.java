package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.MessageConstrant;
import com.example.projectbase.constant.StatusConstant;
import com.example.projectbase.domain.dto.pagination.PaginationRequestDto;
import com.example.projectbase.domain.dto.request.RegisterRequestDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.RegisterDto;
import com.example.projectbase.domain.entity.Register;
import com.example.projectbase.domain.mapper.RegisterMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.CourseRepository;
import com.example.projectbase.repository.MemberRepository;
import com.example.projectbase.repository.RegisterRepository;
import com.example.projectbase.service.RegisterService;
import com.example.projectbase.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final RegisterRepository registerRepository;
    private final RegisterMapper registerMapper;
    private final MemberRepository memberRepository;
    private final CourseRepository courseRepository;

    @Override
    public RegisterDto register(RegisterRequestDto registerRequestDto) {
        Register register = new Register();
        register.setSubscriber(memberRepository.findMemberByid(registerRequestDto.getSubscriberId()));
        register.setCourse(courseRepository.findCourseById(registerRequestDto.getCourseId()));
        register.setStatus(StatusConstant.registerStatus.pending);
        return registerMapper.toDto(registerRepository.save(register));
    }

    @Override
    public List<Register> getAllRegisters(PaginationRequestDto paginationRequestDto) {
        Pageable pageable = PaginationUtil.buildPageable(paginationRequestDto);
        Page<Register> registersPage = registerRepository.findAll(pageable);
        return registersPage.toList();
    }

    @Override
    public List<RegisterDto> findRegistersByName(String name, PaginationRequestDto paginationRequestDto) {
        Pageable pageable = PaginationUtil.buildPageable(paginationRequestDto);
        Page<Register> registersPage = registerRepository.findRegistersBySubscriberFullName(name, pageable);
        return  registersPage.stream().map(registerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public RegisterDto acceptRegister(String id) {
        Optional<Register> register = Optional.ofNullable(registerRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.Register.ERR_NOT_FOUND_ID)));
        register.get().setStatus(StatusConstant.registerStatus.accept);
        return registerMapper.toDto(registerRepository.save(register.get()));
    }

    @Override
    public RegisterDto rejectRegister(String id) {
        Optional<Register> register = Optional.ofNullable(registerRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.Register.ERR_NOT_FOUND_ID)));
        register.get().setStatus(StatusConstant.registerStatus.reject);
        return registerMapper.toDto(registerRepository.save(register.get()));
    }

    @Override
    public CommonResponseDto deleteRegister(String id) {
        Optional<Register> register = Optional.ofNullable(registerRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.Register.ERR_NOT_FOUND_ID)));
        registerRepository.deleteById(id);
        return  new CommonResponseDto(true, MessageConstrant.SUCCESS);
    }
}
