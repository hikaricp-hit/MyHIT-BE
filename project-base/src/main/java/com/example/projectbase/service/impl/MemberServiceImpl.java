package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.MessageConstrant;
import com.example.projectbase.constant.RoleConstant;
import com.example.projectbase.constant.StatusConstant;
import com.example.projectbase.domain.dto.request.MemberChangePassDto;
import com.example.projectbase.domain.dto.request.MemberCreateDto;
import com.example.projectbase.domain.dto.request.MemberUpdateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.MemberResponseDto;
import com.example.projectbase.domain.entity.Member;
import com.example.projectbase.domain.mapper.MemberMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.MemberRepository;
import com.example.projectbase.repository.RoleRepository;
import com.example.projectbase.service.MemberService;
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
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final RoleRepository roleRepository;

    @Override
    public MemberResponseDto createMember(MemberCreateDto memberCreateDto) {
        Member member = memberMapper.toEntity(memberCreateDto);
        member.setNumberCourse(0);
        member.setStatus(StatusConstant.ACTIVE);
        member.setRole(roleRepository.findByRoleName(RoleConstant.USER));
        memberRepository.save(member);
        return memberMapper.toDto(member);
    }

    @Override
    public List<MemberResponseDto> getAllMembers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Member> membersPage = memberRepository.findAll(pageable);
        return membersPage.stream().map(memberMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public MemberResponseDto updateMember(String memberId, MemberUpdateDto memberUpdateDto) {
        Optional<Member> member= memberRepository.findById(memberId);
        member.get().setFullName(memberUpdateDto.getFullName());
        member.get().setEmail(memberUpdateDto.getEmail());
        member.get().setAvatar(memberUpdateDto.getAvatar());
        member.get().setPhone(memberUpdateDto.getPhone());
        member.get().setAddress(memberUpdateDto.getAddress());
        member.get().setClassName(memberUpdateDto.getClassName());
        member.get().setBirth(memberUpdateDto.getBirth());
        member.get().setStatus(memberUpdateDto.getStatus());
        member.get().setNumberCourse(memberUpdateDto.getNumberCourse());
        member.get().setGen(memberUpdateDto.getGen());
        member.get().setQr(memberUpdateDto.getQr());
        memberRepository.save(member.get());
        return memberMapper.toDto(member.get());
    }

    @Override
    public CommonResponseDto changePassword(MemberChangePassDto memberChangePassDto) {
        Optional<Member> member= memberRepository.findById(memberChangePassDto.getMemberId());
        if(member.get().getPassword().equals(memberChangePassDto.getOldPassword())){
            member.get().setPassword(memberChangePassDto.getNewPassword());
            memberRepository.save(member.get());
            return new CommonResponseDto(true, MessageConstrant.SUCCESS);
        }
        else {
            return new CommonResponseDto(false, MessageConstrant.FAIL);
        }
    }

    @Override
    public void deleteMember(String memberId) {
        Optional<Member> member= memberRepository.findById(memberId);
        memberRepository.deleteById(memberId);
    }

    @Override
    public Member findMemberById(String id) throws Exception {
        return memberRepository.findById(id).orElseThrow(() ->new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{id}));
    }

    @Override
    public List<Member> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Member> membersPage = memberRepository.findAll(pageable);
        return membersPage.toList();
    }
}
