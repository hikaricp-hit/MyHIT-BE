package com.example.projectbase.domain.dto.response;

import com.example.projectbase.domain.entity.Course;
import com.example.projectbase.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterDto {
    private String memberID;
    private String courseID;
    private String status;
}
