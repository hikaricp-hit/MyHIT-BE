package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.domain.entity.Member;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.MemberRepository;
import com.example.projectbase.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;

    @Override
    public Member findMemberById(String id) throws Exception {
        return memberRepository.findById(id).orElseThrow(() ->new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{id}));
    }
}
