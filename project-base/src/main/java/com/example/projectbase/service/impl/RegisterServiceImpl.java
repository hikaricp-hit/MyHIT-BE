package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.MessageConstrant;
import com.example.projectbase.constant.StatusConstant;
import com.example.projectbase.constant.ValidationErrorMessages;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.pagination.PagingMeta;
import com.example.projectbase.domain.dto.request.RegisterRequestDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.RegisterDto;
import com.example.projectbase.domain.entity.Course;
import com.example.projectbase.domain.entity.Member;
import com.example.projectbase.domain.entity.Register;
import com.example.projectbase.domain.mapper.RegisterMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.exception.UnauthorizedException;
import com.example.projectbase.repository.CourseRepository;
import com.example.projectbase.repository.MemberRepository;
import com.example.projectbase.repository.RegisterRepository;
import com.example.projectbase.service.RegisterService;
import com.example.projectbase.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
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
        Member member = memberRepository.findById(registerRequestDto.getSubscriberId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Member.ERR_NOT_FOUND_ID));

        Course course = courseRepository.findById(registerRequestDto.getCourseId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Course.ERR_NOT_FOUND_ID));

        if (registerRepository.existsBySubscriberAndCourse(registerRequestDto.getSubscriberId(), registerRequestDto.getCourseId())) {
            throw new ValidationException(ValidationErrorMessages.COURSE_ALREADY_REGISTERED);
        }

        long registeredCoursesCount = registerRepository.countBySubscriber(member);
        if (registeredCoursesCount >= 2) {
            throw new ValidationException(ValidationErrorMessages.MAX_COURSES_REGISTERED);
        }

        Register register = new Register();
        register.setSubscriber(member);
        register.setCourse(course);
        register.setStatus(StatusConstant.registerStatus.pending);

        return registerMapper.toDto(registerRepository.save(register));
    }


    @Override
    public PaginationResponseDto<Register> getAllRegisters(PaginationFullRequestDto paginationRequestDto) {
        Pageable pageable = PaginationUtil.buildPageable(paginationRequestDto);

        Page<Register> registersPage = registerRepository.findAll(pageable);

        PagingMeta pagingMeta = new PagingMeta(
                registersPage.getTotalElements(),
                registersPage.getTotalPages(),
                registersPage.getNumber(),
                registersPage.getSize(),
                paginationRequestDto.getSortBy(),
                paginationRequestDto.getIsAscending().toString()
        );

        return new PaginationResponseDto<>(pagingMeta, registersPage.getContent());
    }


    @Override
    public PaginationResponseDto<Register> findRegistersByName(String name, PaginationFullRequestDto paginationRequestDto) {
        Pageable pageable = PaginationUtil.buildPageable(paginationRequestDto);
        Page<Register> registersPage = registerRepository.findRegistersBySubscriberFullName(name, pageable);
        PagingMeta pagingMeta = new PagingMeta(
                registersPage.getTotalElements(),
                registersPage.getTotalPages(),
                registersPage.getNumber(),
                registersPage.getSize(),
                paginationRequestDto.getSortBy(),
                paginationRequestDto.getIsAscending().toString()
        );
        return new PaginationResponseDto<>(pagingMeta, registersPage.stream().toList());
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

    @Override
    public CommonResponseDto cancelRegister(String registerId, String memberId) {
        Register register = registerRepository.findById(registerId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Register.ERR_NOT_FOUND_ID));

        if (!register.getSubscriber().getId().equals(memberId)) {
            throw new RuntimeException(ErrorMessage.Register.ERR_UNAUTHORIZED_CANCEL);
        }

        registerRepository.deleteById(registerId);
        return new CommonResponseDto(true, MessageConstrant.SUCCESS);
    }


}
