package com.example.projectbase.service;

import com.example.projectbase.domain.entity.Member;

public interface MemberService {
    Member findMemberById(String id) throws Exception;
}
