package com.example.projectbase.service;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.MemberChangePassRequestDto;
import com.example.projectbase.domain.dto.request.MemberCreateDto;
import com.example.projectbase.domain.dto.request.MemberUpdateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.MemberResponseDto;
import com.example.projectbase.domain.entity.Course;
import com.example.projectbase.domain.entity.Member;

import java.util.List;

public interface MemberService {
    MemberResponseDto createMember(MemberCreateDto memberCreateDto);
    PaginationResponseDto<MemberResponseDto> getAllMembers(PaginationFullRequestDto paginationFullRequestDto);
    PaginationResponseDto<Member> getAll(PaginationFullRequestDto paginationFullRequestDto);
    MemberResponseDto updateMember(String memberId, MemberUpdateDto memberUpdateDto);
    CommonResponseDto changePassword(MemberChangePassRequestDto memberChangePassDto);
    CommonResponseDto deleteMember(String memberId);
    Member findMemberById(String id) throws Exception;
    List<Member> getAlls();
}
