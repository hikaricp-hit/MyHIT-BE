package com.example.projectbase.service;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.response.MemberResponseDto;
import com.example.projectbase.domain.dto.response.UserDto;
import com.example.projectbase.domain.entity.Member;
import com.example.projectbase.security.UserPrincipal;

public interface UserService {

  UserDto getUserById(String userId);

  PaginationResponseDto<UserDto> getCustomers(PaginationFullRequestDto request);

  Member getCurrentUser(UserPrincipal principal);

}
