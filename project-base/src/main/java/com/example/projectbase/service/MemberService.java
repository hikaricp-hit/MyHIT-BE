package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.MemberChangePassDto;
import com.example.projectbase.domain.dto.request.MemberCreateDto;
import com.example.projectbase.domain.dto.request.MemberUpdateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.MemberResponseDto;
import com.example.projectbase.domain.entity.Member;

import java.util.List;

public interface MemberService {
    MemberResponseDto createMember(MemberCreateDto memberCreateDto);
    List<MemberResponseDto> getAllMembers(int page, int size);
    MemberResponseDto updateMember(String memberId, MemberUpdateDto memberUpdateDto);
    CommonResponseDto changePassword(MemberChangePassDto memberChangePassDto);
    void deleteMember(String memberId);
    Member findMemberById(String id) throws Exception;
    List<Member> getAll(int page, int size);
}
