package com.example.projectbase.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberResponseDto {
    private String id;
    private String username;
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
