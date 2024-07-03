package com.example.projectbase.domain.dto.request;

import com.example.projectbase.constant.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberUpdateDto {
    private String fullName;
    private String email;
    private String avatar;
    private String phone;
    private String address;
    private String className;
    private String birth;
    private String gen;
    private Integer numberCourse;
    private String status ;
    private String qr;
}

