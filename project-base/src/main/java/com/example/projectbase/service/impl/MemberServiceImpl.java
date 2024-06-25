package com.example.projectbase.service.impl;

import com.example.projectbase.domain.entity.Member;
import com.example.projectbase.repository.MemberRepository;
import com.example.projectbase.service.MemberService;

public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;

    @Override
    public Member findMemberById(String id) throws Exception {
        return memberRepository.findById(id).orElseThrow(() ->{
            return  new Exception("Not found");
        });
    }
}
