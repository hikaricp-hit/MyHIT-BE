package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.request.MemberCreateDto;
import com.example.projectbase.domain.dto.request.MemberUpdateDto;
import com.example.projectbase.domain.dto.request.NotificationCreateDto;
import com.example.projectbase.domain.dto.response.MemberResponseDto;
import com.example.projectbase.domain.dto.response.NotificationDto;
import com.example.projectbase.domain.entity.Member;
import com.example.projectbase.domain.entity.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member toEntity(MemberCreateDto memberCreateDto);
    MemberResponseDto toDto(Member member);
    Member toEntity(MemberUpdateDto memberUpdateDto);
}
